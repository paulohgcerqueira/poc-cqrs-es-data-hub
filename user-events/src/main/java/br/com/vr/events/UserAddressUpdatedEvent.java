package br.com.vr.events;

import lombok.Data;

@Data
public class UserAddressUpdatedEvent {

    private String userId;
    private String city;
    private String state;
    private String postcode;

}
