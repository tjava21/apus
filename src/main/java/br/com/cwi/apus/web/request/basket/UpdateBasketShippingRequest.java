package br.com.cwi.apus.web.request.basket;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateBasketShippingRequest {

    @NotEmpty
    private String zip;

    @NotEmpty
    private String address;
}
