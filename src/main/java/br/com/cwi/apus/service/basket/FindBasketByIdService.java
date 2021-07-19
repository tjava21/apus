package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.exception.NotFoundException;
import br.com.cwi.apus.mapper.BasketMapper;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.web.response.basket.BasketResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.cwi.apus.utils.DomainUtils.toInternalId;

@AllArgsConstructor
@Service
public class FindBasketByIdService {

    private BasketRepository repository;
    private BasketMapper mapper;

    public BasketResponse execute(String id) {
        return mapper.toBasketResponse(getDomain(id));
    }

    public Basket getDomain(String id) {
        return repository.findById(toInternalId(id))
                .orElseThrow(() -> new NotFoundException("basket"));
    }
}
