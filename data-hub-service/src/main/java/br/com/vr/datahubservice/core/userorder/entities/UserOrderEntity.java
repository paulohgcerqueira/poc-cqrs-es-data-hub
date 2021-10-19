package br.com.vr.datahubservice.core.userorder.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="user_order")
@Data
public class UserOrderEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_last_name")
    private String userLastName;

    @Column(name = "user_city")
    private String userCity;

    @Column(name = "user_state")
    private String userState;

    @Column(name = "user_postcode")
    private String userPostcode;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_total")
    private BigDecimal orderTotal;


}
