package br.com.vr.orderservice.command.order;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateOrderCommand {

    private String orderId;
    private String userId;
    private String orderNumber;
    private BigDecimal orderTotal;

}
