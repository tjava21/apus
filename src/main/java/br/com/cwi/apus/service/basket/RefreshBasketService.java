package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.domain.BasketItem;
import br.com.cwi.apus.external.cetus.ShippingService;
import br.com.cwi.apus.external.cetus.response.ShippingQuoteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.util.Objects.nonNull;

@AllArgsConstructor
@Service
public class RefreshBasketService {

    private ShippingService shippingService;

    public void execute(Basket basket) {

        BigDecimal totalItems = ZERO;
        BigDecimal totalShipping = ZERO;
        BigDecimal total = ZERO;
        int volume = 0;
        Integer time = null;

        for (BasketItem item: basket.getItems()) {
            int itemQuantity = item.getQuantity();
            int itemVolume = item.getProduct().getVolume();
            BigDecimal itemPrice = item.getProduct().getPrice();

            totalItems = totalItems.add(itemPrice.multiply(valueOf(itemQuantity)));
            volume += itemVolume * itemQuantity;
        }

        String zipCode = basket.getZip();

        if (nonNull(zipCode)) {

            ShippingQuoteResponse response = shippingService.quote(zipCode, volume);

            if (nonNull(response)) {
                basket.setShippingId(response.getId());
                time = response.getTime();
                totalShipping = response.getPrice();
            }
        }

        total = totalItems.add(totalShipping);

        basket.setTotalItems(totalItems);
        basket.setTotalShipping(totalShipping);
        basket.setTotal(total);
        basket.setVolume(volume);
        basket.setTime(time);
    }
}
