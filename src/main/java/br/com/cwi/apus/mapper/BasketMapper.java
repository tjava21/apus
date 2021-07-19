package br.com.cwi.apus.mapper;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.domain.BasketItem;
import br.com.cwi.apus.web.response.basket.BasketResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.apus.utils.DomainUtils.toExternalId;
import static java.util.Objects.isNull;

@Component
public class BasketMapper {

    public BasketResponse toBasketResponse(Basket entity) {

        BasketResponse response = new BasketResponse();
        response.setId(toExternalId(entity.getId()));
        response.setTotal(entity.getTotal());
        response.setTotalItems(entity.getTotalItems());
        response.setTotalShipping(entity.getTotalShipping());
        response.setVolume(entity.getVolume());
        response.setTime(entity.getTime());
        response.setCustomer(toBasketCustomer(entity));
        response.setShipping(toBasketShipping(entity));
        response.setItems(toBasketItems(entity.getItems()));

        return response;
    }

    private BasketResponse.BasketShippingResponse toBasketShipping(Basket entity) {

        if (isNull(entity)) return null;

        BasketResponse.BasketShippingResponse response = new BasketResponse.BasketShippingResponse();
        response.setAddress(entity.getAddress());
        response.setZip(entity.getZip());

        return response;
    }

    private BasketResponse.BasketCustomerResponse toBasketCustomer(Basket entity) {

        if (isNull(entity)) return null;

        BasketResponse.BasketCustomerResponse response = new BasketResponse.BasketCustomerResponse();
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());

        return response;
    }

    private List<BasketResponse.BasketItemResponse> toBasketItems(List<BasketItem> entityItems) {
        return entityItems.stream().map(e -> {

            BasketResponse.BasketItemResponse response = new BasketResponse.BasketItemResponse();
            response.setId(toExternalId(e.getId()));
            response.setQuantity(e.getQuantity());
            response.setVolume(e.getProduct().getVolume());
            response.setPrice(e.getProduct().getPrice());

            return response;
        }).collect(Collectors.toList());
    }
}
