package br.com.cwi.apus.web;

import br.com.cwi.apus.external.order.request.StockRequest;
import br.com.cwi.apus.service.product.RefreshProductStockService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {

    private RefreshProductStockService refreshProductStockService;

    @PutMapping
    public void execute(@RequestBody @Validated StockRequest request){
        refreshProductStockService.execute(request);
    }
}
