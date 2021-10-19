package br.com.vr.userservice.command.user;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateUserCommand {

    private String userId;
    private String firstName;
    private String lastName;

}
