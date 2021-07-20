package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.domain.order.Order;
import br.com.cwi.apus.domain.order.OrderStatus;
import br.com.cwi.apus.external.lyra.PaymentService;
import br.com.cwi.apus.mapper.OrderMapper;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.service.order.CreateOrderService;
import br.com.cwi.apus.web.response.order.OrderResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.cwi.apus.utils.DomainUtils.toExternalId;

@SpringBootTest
public class CheckoutBasketServiceTest {

    @InjectMocks
    private CheckoutBasketService tested;

    @Mock
    private FindBasketByIdService findBasketByIdService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private BasketRepository repository;

    @Mock
    private CreateOrderService createOrderService;

    @Mock
    private OrderMapper mapper;

    @Test
    public void shouldCheckoutBasketService() {
        // #1 - ARRANGE
        Basket basket = Basket.builder().id(1L).card("1234 4321").total(BigDecimal.TEN).build();
        Order order = Order.builder().id(UUID.randomUUID()).status(OrderStatus.CRIADO).build();

        Mockito.when(findBasketByIdService.getDomain(toExternalId(basket.getId()))).thenReturn(basket);
        Mockito.when(paymentService.inform(basket.getCard(), basket.getTotal())).thenReturn(UUID.randomUUID().toString());
        Mockito.when(createOrderService.execute(basket)).thenReturn(order);

        // #2 - ACT
        OrderResponse result = tested.execute(toExternalId(basket.getId()));

        // #3 - ASSERT
        Mockito.verify(findBasketByIdService).getDomain(toExternalId(basket.getId()));
        Mockito.verify(paymentService).inform(basket.getCard(), basket.getTotal());
        Mockito.verify(repository).save(basket);
        Mockito.verify(createOrderService).execute(basket);
        Mockito.verify(mapper).toOrderResponse(order);
    }
}