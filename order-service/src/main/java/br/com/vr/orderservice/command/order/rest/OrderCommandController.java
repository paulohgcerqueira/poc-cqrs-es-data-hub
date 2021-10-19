package br.com.vr.orderservice.command.order.rest;

import br.com.vr.orderservice.command.order.CreateOrderCommand;
import br.com.vr.orderservice.command.order.rest.dtos.CreateOrderRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private CommandGateway commandGateway;

    @Autowired
    public OrderCommandController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody CreateOrderRequest user) {
        CreateOrderCommand command = CreateOrderCommand.builder()
                .userId(user.getUserId())
                .orderNumber(user.getOrderNumber())
                .orderTotal(user.getOrderTotal())
                .orderId(UUID.randomUUID().toString()).build();
        return commandGateway.send(command);
    }

}
