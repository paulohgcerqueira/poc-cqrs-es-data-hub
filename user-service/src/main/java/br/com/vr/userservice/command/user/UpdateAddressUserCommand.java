package br.com.vr.userservice.command.user;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.persistence.Column;
import java.util.Set;

@Data
@Builder
public class UpdateAddressUserCommand {

    @TargetAggregateIdentifier
    private String userId;
    private String city;
    private String state;
    private String postcode;

}
