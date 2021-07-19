package br.com.cwi.apus.service.product;

import br.com.cwi.apus.mapper.ProductMapper;
import br.com.cwi.apus.repository.ProductRepository;
import br.com.cwi.apus.web.response.product.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class FindProductsService {

    private ProductRepository repository;
    private ProductMapper mapper;

    public List<ProductResponse> execute() {
        return repository.findAll()
                .stream().map(mapper::toProductResponse)
                .collect(toList());
    }
}
