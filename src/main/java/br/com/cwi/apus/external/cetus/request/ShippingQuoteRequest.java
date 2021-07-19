package br.com.cwi.apus.external.cetus.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShippingQuoteRequest {

    private String zipOrigin;
    private String zipDestination;
    private int volume;
}
