package br.com.cwi.apus.mapper;

import br.com.cwi.apus.domain.Product;
import br.com.cwi.apus.web.response.product.ProductDetailResponse;
import br.com.cwi.apus.web.response.product.ProductResponse;
import org.springframework.stereotype.Component;

import static br.com.cwi.apus.utils.DomainUtils.toExternalId;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product entity) {
        ProductResponse response = new ProductResponse();
        response.setId(toExternalId(entity.getId()));
        response.setDescription(entity.getDescription());
        return response;
    }

    public ProductDetailResponse toProductDetailResponse(Product entity) {
        ProductDetailResponse response = new ProductDetailResponse();
        response.setId(toExternalId(entity.getId()));
        response.setDescription(entity.getDescription());
        response.setVolume(entity.getVolume());
        response.setQuantity(entity.getQuantity());
        response.setPrice(entity.getPrice());
        return response;
    }
}
