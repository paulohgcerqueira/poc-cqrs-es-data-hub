package br.com.vr.userservice.query.user.rest;

import br.com.vr.userservice.query.user.ListUsersQuery;
import br.com.vr.userservice.query.user.rest.dtos.UserResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<UserResponse> list(){
        return queryGateway.query(new ListUsersQuery(), ResponseTypes.multipleInstancesOf(UserResponse.class)).join();
    }
}
