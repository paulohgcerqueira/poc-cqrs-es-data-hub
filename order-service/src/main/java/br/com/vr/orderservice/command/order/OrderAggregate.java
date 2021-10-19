package br.com.vr.orderservice.command.order;


import br.com.vr.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String userId;
    private String orderNumber;
    private BigDecimal orderTotal;

    public OrderAggregate(){}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        log.info("Handling command {} : {}", createOrderCommand.getClass().getCanonicalName(), createOrderCommand);
        createOrderCommandValidation(createOrderCommand);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);

        apply(orderCreatedEvent);
        log.info("Done handling command {} : {}", createOrderCommand.getClass().getCanonicalName(), createOrderCommand);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        log.info("Handling event {} : {}", event.getClass().getCanonicalName(), event);
        this.orderId = event.getOrderId();
        this.userId = event.getUserId();
        this.orderNumber = event.getOrderNumber();
        this.orderTotal = event.getOrderTotal();
        log.info("Done handling event {} : {}", event.getClass().getCanonicalName(), event);
    }

    public void createOrderCommandValidation(CreateOrderCommand createOrderCommand) throws IllegalArgumentException {
        if(createOrderCommand.getOrderNumber().isEmpty()){
            throw new IllegalArgumentException("O campo order number é obrigatório");
        }else if(createOrderCommand.getOrderTotal() != null &&
                createOrderCommand.getOrderTotal().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O campo order total é obrigatório, e deve ser maior que zero");
        }
    }
}
