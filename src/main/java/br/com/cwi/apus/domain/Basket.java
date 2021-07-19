package br.com.cwi.apus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Basket {

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BasketItem> items = new ArrayList<>();
}
