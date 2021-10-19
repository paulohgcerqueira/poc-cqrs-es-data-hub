package br.com.vr.events;

import lombok.Data;

@Data
public class UserCreatedEvent {

    private String userId;
    private String firstName;
    private String lastName;

}
