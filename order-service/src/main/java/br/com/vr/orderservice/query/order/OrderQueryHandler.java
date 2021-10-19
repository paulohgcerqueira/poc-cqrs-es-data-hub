package br.com.vr.orderservice.query.order;

import br.com.vr.orderservice.core.order.entities.OrderEntity;
import br.com.vr.orderservice.core.order.repository.OrderRepository;
import br.com.vr.orderservice.query.order.rest.dtos.OrderResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderQueryHandler {

    private OrderRepository orderRepository;

    public OrderQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @QueryHandler
    public List<OrderResponse> list(ListOrdersQuery query) {
        List<OrderResponse> ordersResponse = new ArrayList<>();
        List<OrderEntity> ordersEntities =  orderRepository.findAll();

        for(OrderEntity orderEntity: ordersEntities) {
            OrderResponse orderResponse = new OrderResponse();
            BeanUtils.copyProperties(orderEntity, orderResponse);
            ordersResponse.add(orderResponse);
        }

        return ordersResponse;
    }

}
