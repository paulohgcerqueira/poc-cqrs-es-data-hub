package br.com.vr.userservice.command.user.rest.dtos;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String firstName;
    private String lastName;

}
