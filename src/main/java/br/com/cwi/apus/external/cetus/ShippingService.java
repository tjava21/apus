package br.com.cwi.apus.external.cetus;

import br.com.cwi.apus.external.cetus.request.ShippingQuoteRequest;
import br.com.cwi.apus.external.cetus.response.ShippingQuoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ShippingService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.cetus.url}")
    private String url;

    @Value("${app.cetus.zip}")
    private String zipOrigin;

    public ShippingQuoteResponse quote(String zipDestination, int volume) {

        try {

            ShippingQuoteRequest request = ShippingQuoteRequest.builder()
                    .zipOrigin(zipOrigin).zipDestination(zipDestination).volume(volume)
                    .build();

            ShippingQuoteResponse response = restTemplate
                    .postForObject(url, request, ShippingQuoteResponse.class);

            return response;

        } catch (Exception e) {
            log.error("Erro ao cotar frete (CETUS)", e);
            return null;
        }
    }
}
