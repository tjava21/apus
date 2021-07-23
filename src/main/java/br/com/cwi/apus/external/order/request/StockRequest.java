package br.com.cwi.apus.external.order.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class StockRequest {

    @NotNull
    private String id;

    @Positive
    private int quantity;
}
