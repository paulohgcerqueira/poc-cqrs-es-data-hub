package br.com.vr.datahubservice.query.userorder.rest.dtos;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class UserOrderResponse {

    private String orderId;
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userCity;
    private String userState;
    private String userPostcode;
    private String orderNumber;
    private BigDecimal orderTotal;

}
