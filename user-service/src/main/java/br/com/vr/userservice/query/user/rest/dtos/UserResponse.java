package br.com.vr.userservice.query.user.rest.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String postcode;

}
