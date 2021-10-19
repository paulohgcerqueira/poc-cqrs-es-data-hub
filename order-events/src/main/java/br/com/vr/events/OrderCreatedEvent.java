package br.com.vr.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String userId;
    private String orderNumber;
    private BigDecimal orderTotal;

}
