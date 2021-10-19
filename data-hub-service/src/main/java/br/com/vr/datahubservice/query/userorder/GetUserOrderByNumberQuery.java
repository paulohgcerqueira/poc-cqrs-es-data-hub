package br.com.vr.datahubservice.query.userorder;

import lombok.Value;

@Value
public class GetUserOrderByNumberQuery {

    private final String orderNumber;

}
