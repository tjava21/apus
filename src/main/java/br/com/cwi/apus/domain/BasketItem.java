package br.com.cwi.apus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class BasketItem {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    @ManyToOne
    private Basket basket;

    @ManyToOne
    private Product product;
}
