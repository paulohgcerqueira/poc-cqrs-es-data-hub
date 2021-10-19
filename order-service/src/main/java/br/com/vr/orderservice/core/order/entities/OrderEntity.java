package br.com.vr.orderservice.core.order.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="orders")
@Data
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_total")
    private BigDecimal orderTotal;
}
