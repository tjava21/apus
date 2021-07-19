package br.com.cwi.apus.mapper;

import br.com.cwi.apus.domain.order.Order;
import br.com.cwi.apus.web.response.order.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order entity) {

        OrderResponse response = new OrderResponse();
        response.setId(entity.getId().toString());
        return response;
    }
}
