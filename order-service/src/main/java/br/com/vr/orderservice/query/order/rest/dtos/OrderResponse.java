package br.com.vr.orderservice.query.order.rest.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {

    private String orderId;
    private String userId;
    private String orderNumber;
    private BigDecimal orderTotal;
}
