package br.com.vr.userservice.command.user.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class UpdateUserAddressRequest {

    private String city;
    private String state;
    private String postcode;

}
