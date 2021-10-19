package br.com.vr.orderservice.query.order.rest;

import br.com.vr.orderservice.query.order.ListOrdersQuery;
import br.com.vr.orderservice.query.order.rest.dtos.OrderResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<OrderResponse> list(){
        return queryGateway.query(new ListOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderResponse.class)).join();
    }

}
