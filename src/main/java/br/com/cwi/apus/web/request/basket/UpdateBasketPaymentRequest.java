package br.com.cwi.apus.web.request.basket;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateBasketPaymentRequest {

    @NotEmpty
    private String card;
}
