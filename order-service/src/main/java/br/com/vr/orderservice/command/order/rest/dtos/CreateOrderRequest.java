package br.com.vr.orderservice.command.order.rest.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {

    private String userId;
    private String orderNumber;
    private BigDecimal orderTotal;

}
