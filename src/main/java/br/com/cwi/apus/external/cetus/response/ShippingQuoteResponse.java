package br.com.cwi.apus.external.cetus.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShippingQuoteResponse {

    private String id;
    private BigDecimal price;
    private int time;
}
