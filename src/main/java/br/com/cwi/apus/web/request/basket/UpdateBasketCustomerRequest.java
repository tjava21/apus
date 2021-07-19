package br.com.cwi.apus.web.request.basket;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UpdateBasketCustomerRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;
}
