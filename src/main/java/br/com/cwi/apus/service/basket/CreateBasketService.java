package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.mapper.BasketMapper;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.web.response.basket.BasketResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CreateBasketService {

    private BasketRepository repository;
    private BasketMapper mapper;

    @Transactional
    public BasketResponse execute() {
        return mapper.toBasketResponse(repository.save(new Basket()));
    }
}
