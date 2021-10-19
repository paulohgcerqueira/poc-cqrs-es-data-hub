package br.com.vr.datahubservice.query.userorder;

import lombok.Value;

@Value
public class GetUserOrderByUserQuery {

    private final String userId;
}
