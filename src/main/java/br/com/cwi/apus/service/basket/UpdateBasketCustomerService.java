package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.web.request.basket.UpdateBasketCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateBasketCustomerService {

    private FindBasketByIdService findBasketByIdService;
    private RefreshBasketService refreshBasketService;
    private BasketRepository repository;

    @Transactional
    public void execute(String id, UpdateBasketCustomerRequest request) {

        Basket basket = findBasketByIdService.getDomain(id);

        basket.setName(request.getName());
        basket.setEmail(request.getEmail());

        repository.save(basket);
    }
}
