package br.com.vr.userservice.command.user.rest;

import br.com.vr.userservice.command.user.CreateUserCommand;
import br.com.vr.userservice.command.user.UpdateAddressUserCommand;
import br.com.vr.userservice.command.user.rest.dtos.CreateUserRequest;
import br.com.vr.userservice.command.user.rest.dtos.UpdateUserAddressRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
public class UserCommandController {

    private CommandGateway commandGateway;

    @Autowired
    public UserCommandController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody CreateUserRequest user) {
        CreateUserCommand command = CreateUserCommand.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userId(UUID.randomUUID().toString()).build();
        return commandGateway.send(command);
    }

    @PutMapping(path="/{userId}")
    public CompletableFuture<String> updateAddress(@RequestBody UpdateUserAddressRequest user, @PathVariable String userId) {
        UpdateAddressUserCommand command = UpdateAddressUserCommand.builder()
                .city(user.getCity())
                .postcode(user.getPostcode())
                .state(user.getState())
                .userId(userId).build();
        return commandGateway.send(command);
    }

}
