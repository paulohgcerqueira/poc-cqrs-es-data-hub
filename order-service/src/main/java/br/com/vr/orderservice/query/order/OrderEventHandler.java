package br.com.vr.orderservice.query.order;

import br.com.vr.events.OrderCreatedEvent;
import br.com.vr.orderservice.core.order.entities.OrderEntity;
import br.com.vr.orderservice.core.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        log.info("orderCreatedEvent found : {}", orderCreatedEvent);
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, orderEntity);
        orderRepository.save(orderEntity);
    }
}
