package br.com.vr.datahubservice.query.userorder;

import br.com.vr.datahubservice.core.userorder.entities.UserOrderEntity;
import br.com.vr.datahubservice.core.userorder.repository.UserOrderRepository;
import br.com.vr.events.OrderCreatedEvent;
import br.com.vr.events.UserAddressUpdatedEvent;
import br.com.vr.events.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserOrderEventHandler {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEventHandler(UserOrderRepository userOrderRepository) { this.userOrderRepository = userOrderRepository; }

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent){
        log.info("userCreatedEvent found : {}", userCreatedEvent);
        UserOrderEntity userOrderEntity = new UserOrderEntity();

        userOrderEntity.setUserFirstName(userCreatedEvent.getFirstName());
        userOrderEntity.setUserLastName(userCreatedEvent.getLastName());
        userOrderEntity.setUserId(userCreatedEvent.getUserId());

        userOrderRepository.save(userOrderEntity);
    }

    @EventHandler
    public void on(UserAddressUpdatedEvent userAddressUpdatedEvent){
        log.info("userAddressUpdatedEvent found : {}", userAddressUpdatedEvent);
        Optional<UserOrderEntity> userOpt = userOrderRepository.findById(userAddressUpdatedEvent.getUserId());
        if(userOpt.isPresent()){
            UserOrderEntity userEntity = userOpt.get();
            userEntity.setUserCity(userAddressUpdatedEvent.getCity());
            userEntity.setUserState(userAddressUpdatedEvent.getState());
            userEntity.setUserPostcode(userAddressUpdatedEvent.getPostcode());
            userOrderRepository.save(userEntity);
        }
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        log.info("orderCreatedEvent found : {}", orderCreatedEvent);
        Optional<UserOrderEntity> userOpt = userOrderRepository.findById(orderCreatedEvent.getUserId());
        if(userOpt.isPresent()){
            UserOrderEntity userEntity = userOpt.get();
            userEntity.setOrderId(orderCreatedEvent.getOrderId());
            userEntity.setOrderNumber(orderCreatedEvent.getOrderNumber());
            userEntity.setOrderTotal(orderCreatedEvent.getOrderTotal());
            userOrderRepository.save(userEntity);
        }
    }

}
