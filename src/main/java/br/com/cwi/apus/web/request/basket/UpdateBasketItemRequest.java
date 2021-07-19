package br.com.cwi.apus.web.request.basket;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class UpdateBasketItemRequest {

    @NotEmpty
    private String id;

    @Positive
    private int quantity;
}
