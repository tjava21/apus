package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.exception.IntegrationException;
import br.com.cwi.apus.external.lyra.PaymentService;
import br.com.cwi.apus.external.order.CreateOrderService;
import br.com.cwi.apus.external.order.request.CreateOrderRequest;
import br.com.cwi.apus.external.order.response.CreateOrderResponse;
import br.com.cwi.apus.repository.BasketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class CheckoutBasketService {

    private FindBasketByIdService findBasketByIdService;
    private PaymentService paymentService;
    private BasketRepository repository;
    private CreateOrderService createOrderService;

    @Transactional
    public CreateOrderResponse execute(String id) {

        Basket basket = findBasketByIdService.getDomain(id);

        String paymentId = paymentService.inform(basket.getCard(), basket.getTotal());

        if (isNull(paymentId)) {
            throw new IntegrationException("payment");
        }

        basket.setPaymentId(paymentId);
        repository.save(basket);

        CreateOrderRequest createOrderRequest = buildOrder(basket);

        CreateOrderResponse response = createOrderService.execute(createOrderRequest);

        return response;
    }

    private CreateOrderRequest buildOrder(Basket basket) {
        return CreateOrderRequest.builder()
                .id(basket.getId())
                .totalItems(basket.getTotalItems())
                .totalShipping(basket.getTotalShipping())
                .total(basket.getTotal())
                .time(basket.getTime())
                .volume(basket.getVolume())
                .name(basket.getName())
                .email(basket.getEmail())
                .card(basket.getCard())
                .zip(basket.getZip())
                .address(basket.getAddress())
                .paymentId(basket.getPaymentId())
                .shippingId(basket.getShippingId())
                .items(basket.getItems().stream()
                        .map(o ->
                                CreateOrderRequest.CreateOrderItemRequest.builder()
                                        .id(o.getProduct().getId())
                                        .quantity(o.getQuantity())
                                        .description(o.getProduct().getDescription())
                                        .volume(o.getProduct().getVolume())
                                        .price(o.getProduct().getPrice())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
