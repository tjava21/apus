package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.external.lyra.PaymentService;
import br.com.cwi.apus.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.cwi.apus.utils.DomainUtils.toExternalId;

@SpringBootTest
public class CheckoutBasketServiceTest {

//    @InjectMocks
//    private CheckoutBasketService tested;

    @Mock
    private FindBasketByIdService findBasketByIdService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private BasketRepository repository;



    @Test
    public void shouldCheckoutBasketService() {
        // #1 - ARRANGE
        Basket basket = Basket.builder().id(1L).card("1234 4321").total(BigDecimal.TEN).build();

        Mockito.when(findBasketByIdService.getDomain(toExternalId(basket.getId()))).thenReturn(basket);
        Mockito.when(paymentService.inform(basket.getCard(), basket.getTotal())).thenReturn(UUID.randomUUID().toString());

        // #2 - ACT
//        OrderResponse result = tested.execute(toExternalId(basket.getId()));

        // #3 - ASSERT
        Mockito.verify(findBasketByIdService).getDomain(toExternalId(basket.getId()));
        Mockito.verify(paymentService).inform(basket.getCard(), basket.getTotal());
        Mockito.verify(repository).save(basket);
    }
}