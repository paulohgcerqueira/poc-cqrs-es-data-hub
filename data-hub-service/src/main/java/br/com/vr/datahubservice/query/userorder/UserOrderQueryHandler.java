package br.com.vr.datahubservice.query.userorder;

import br.com.vr.datahubservice.core.userorder.entities.UserOrderEntity;
import br.com.vr.datahubservice.core.userorder.repository.UserOrderRepository;
import br.com.vr.datahubservice.query.userorder.rest.dtos.UserOrderResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserOrderQueryHandler {

    private UserOrderRepository userOrderRepository;

    public UserOrderQueryHandler(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @QueryHandler
    public List<UserOrderResponse> get(GetUserOrderByUserQuery query) {
        List<UserOrderResponse> userOrdersResponse = new ArrayList<>();
        List<UserOrderEntity> usersOrdersEntities =  userOrderRepository.findByUserId(query.getUserId());

        for(UserOrderEntity userOrderEntity: usersOrdersEntities) {
            UserOrderResponse userOrderResponse = new UserOrderResponse();
            BeanUtils.copyProperties(userOrderEntity, userOrderResponse);
            userOrdersResponse.add(userOrderResponse);
        }

        return userOrdersResponse;

    }
}
