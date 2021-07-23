package br.com.cwi.apus.external.order;

import br.com.cwi.apus.external.order.request.CreateOrderRequest;
import br.com.cwi.apus.external.order.response.CreateOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CreateOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.order.url}")
    private String url;

    public CreateOrderResponse execute(CreateOrderRequest request) {

        try {
            CreateOrderResponse response = restTemplate
                    .postForObject(url, request, CreateOrderResponse.class);

            return response;
        } catch (Exception e) {
            log.error("Erro ao criar pedido (APUS-ORDER)", e);
            return null;
        }

    }
}
