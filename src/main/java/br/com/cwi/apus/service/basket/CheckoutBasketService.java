package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.exception.IntegrationException;
import br.com.cwi.apus.external.lyra.PaymentService;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.web.response.order.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class CheckoutBasketService {

    private FindBasketByIdService findBasketByIdService;
    private PaymentService paymentService;
    private BasketRepository repository;

    @Transactional
    public OrderResponse execute(String id) {

        Basket basket = findBasketByIdService.getDomain(id);

        String paymentId = paymentService.inform(basket.getCard(), basket.getTotal());

        if (isNull(paymentId)) {
            throw new IntegrationException("payment");
        }

        basket.setPaymentId(paymentId);
        repository.save(basket);


        return null;
    }
}
