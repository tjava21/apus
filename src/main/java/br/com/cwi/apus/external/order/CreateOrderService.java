package br.com.cwi.apus.external.order;

import br.com.cwi.apus.external.order.request.CreateOrderRequest;
import br.com.cwi.apus.external.order.response.CreateOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.apus.configuration.RabbitMQConfiguration.EXCHANGE;
import static br.com.cwi.apus.configuration.RabbitMQConfiguration.ROUTING_KEY;

@Slf4j
@Service
public class CreateOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public CreateOrderResponse execute(CreateOrderRequest request) {

        try {
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, request);
        } catch (Exception e) {
            log.error("Erro ao criar pedido (APUS-ORDER)", e);
        }

        return null;
    }
}
