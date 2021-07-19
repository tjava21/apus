package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.domain.BasketItem;
import br.com.cwi.apus.domain.Product;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.service.product.FindProductByIdService;
import br.com.cwi.apus.web.request.basket.UpdateBasketItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateBasketItemService {

    private FindBasketByIdService findBasketByIdService;
    private FindProductByIdService findProductByIdService;
    private RefreshBasketService refreshBasketService;
    private BasketRepository repository;

    @Transactional
    public void execute(String id, UpdateBasketItemRequest request) {

        Basket basket = findBasketByIdService.getDomain(id);

        Product product = findProductByIdService.getDomain(request.getId());

        BasketItem item = BasketItem.builder()
                .quantity(request.getQuantity())
                .basket(basket)
                .product(product)
                .build();

        basket.getItems().add(item);

        refreshBasketService.execute(basket);

        repository.save(basket);
    }
}
