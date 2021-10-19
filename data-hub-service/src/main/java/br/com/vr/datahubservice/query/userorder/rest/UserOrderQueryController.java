package br.com.vr.datahubservice.query.userorder.rest;

import br.com.vr.datahubservice.query.userorder.GetUserOrderByNumberQuery;
import br.com.vr.datahubservice.query.userorder.GetUserOrderByUserQuery;
import br.com.vr.datahubservice.query.userorder.rest.dtos.UserOrderResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-order")
public class UserOrderQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping(value = "/{user-id}")
    public List<UserOrderResponse> getByUser(@PathVariable("user-id") String userId){
        return queryGateway.query(new GetUserOrderByUserQuery(userId), ResponseTypes.multipleInstancesOf(UserOrderResponse.class)).join();
    }

    @GetMapping
    public List<UserOrderResponse> getByNumber(@RequestParam("order-number") String orderNumber){
        return queryGateway.query(new GetUserOrderByNumberQuery(orderNumber), ResponseTypes.multipleInstancesOf(UserOrderResponse.class)).join();
    }
}
