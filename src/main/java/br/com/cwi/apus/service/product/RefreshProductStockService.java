package br.com.cwi.apus.service.product;

import br.com.cwi.apus.domain.Product;
import br.com.cwi.apus.external.order.request.StockRequest;
import br.com.cwi.apus.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.apus.utils.DomainUtils.toInternalId;

@AllArgsConstructor
@Service
public class RefreshProductStockService {

    private ProductRepository productRepository;

    @Transactional
    public void execute(StockRequest request) {

        Long productId = toInternalId(request.getId());
        int quantity = request.getQuantity();

        Product product = productRepository.getById(productId);
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
