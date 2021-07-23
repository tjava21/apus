package br.com.cwi.apus.external.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateOrderRequest {

    private Long id;
    private BigDecimal totalItems;
    private BigDecimal totalShipping;
    private BigDecimal total;
    private Integer time;
    private int volume;

    private String name;
    private String email;
    private String card;

    private String zip;
    private String address;

    private String paymentId;
    private String shippingId;

    private List<CreateOrderItemRequest> items = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class CreateOrderItemRequest {

        private Long id;
        private int quantity;
        private String description;
        private int volume;
        private BigDecimal price;
    }
}
