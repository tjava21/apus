package br.com.cwi.apus.service.product;

import br.com.cwi.apus.domain.Product;
import br.com.cwi.apus.exception.NotFoundException;
import br.com.cwi.apus.mapper.ProductMapper;
import br.com.cwi.apus.repository.ProductRepository;
import br.com.cwi.apus.web.response.product.ProductDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.cwi.apus.utils.DomainUtils.toInternalId;

@AllArgsConstructor
@Service
public class FindProductByIdService {

    private ProductRepository repository;
    private ProductMapper mapper;

    public ProductDetailResponse execute(String id) {
        return mapper.toProductDetailResponse(getDomain(id));
    }

    public Product getDomain(String id) {
        return repository.findById(toInternalId(id))
                .orElseThrow(() -> new NotFoundException("product"));
    }
}
