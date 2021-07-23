package br.com.cwi.apus.web;

import br.com.cwi.apus.service.basket.*;
import br.com.cwi.apus.web.request.basket.UpdateBasketCustomerRequest;
import br.com.cwi.apus.web.request.basket.UpdateBasketItemRequest;
import br.com.cwi.apus.web.request.basket.UpdateBasketPaymentRequest;
import br.com.cwi.apus.web.request.basket.UpdateBasketShippingRequest;
import br.com.cwi.apus.web.response.basket.BasketResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/basket")
public class BasketController {

    private CreateBasketService createBasketService;
    private FindBasketByIdService findBasketByIdService;
    private UpdateBasketItemService updateBasketItemService;
    private UpdateBasketCustomerService updateBasketCustomerService;
    private UpdateBasketShippingService updateBasketShippingService;
    private UpdateBasketPaymentService updateBasketPaymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasketResponse create() {
        return createBasketService.execute();
    }

    @GetMapping("/{id}")
    public BasketResponse find(@PathVariable("id") String id) {
        return findBasketByIdService.execute(id);
    }

    @PutMapping("/{id}/item")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable("id") String id, @RequestBody @Validated UpdateBasketItemRequest request) {
        updateBasketItemService.execute(id, request);
    }

    @PutMapping("/{id}/identity")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable("id") String id, @RequestBody @Validated UpdateBasketCustomerRequest request) {
        updateBasketCustomerService.execute(id, request);
    }

    @PutMapping("/{id}/address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateShipping(@PathVariable("id") String id, @RequestBody @Validated UpdateBasketShippingRequest request) {
        updateBasketShippingService.execute(id, request);
    }

    @PutMapping("/{id}/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePayment(@PathVariable("id") String id, @RequestBody @Validated UpdateBasketPaymentRequest request) {
        updateBasketPaymentService.execute(id, request);
    }

//    @PutMapping("/{id}/checkout")
//    public OrderResponse checkout(@PathVariable("id") String id) {
//        return checkoutBasketService.execute(id);
//    }
}
