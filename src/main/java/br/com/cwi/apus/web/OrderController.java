package br.com.cwi.apus.web;

import br.com.cwi.apus.external.order.CreateOrderService;
import br.com.cwi.apus.external.order.request.CreateOrderRequest;
import br.com.cwi.apus.external.order.response.CreateOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private CreateOrderService createOrderService;

    @PostMapping
    public CreateOrderResponse create(@RequestBody CreateOrderRequest request) {
        return createOrderService.execute(request);
    }
}
