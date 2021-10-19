package br.com.vr.userservice.query.user;

import br.com.vr.events.UserAddressUpdatedEvent;
import br.com.vr.events.UserCreatedEvent;
import br.com.vr.userservice.core.user.entities.UserEntity;
import br.com.vr.userservice.core.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserEventHandler {

    private final UserRepository userRepository;

    public UserEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent){
        log.info("userCreatedEvent found : {}", userCreatedEvent);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userCreatedEvent, userEntity);
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(UserAddressUpdatedEvent userAddressUpdatedEvent){
        log.info("userAddressUpdatedEvent found : {}", userAddressUpdatedEvent);
        Optional<UserEntity> userOpt = userRepository.findById(userAddressUpdatedEvent.getUserId());
        if(userOpt.isPresent()){
            UserEntity userEntity = userOpt.get();
            BeanUtils.copyProperties(userAddressUpdatedEvent, userEntity);
            userRepository.save(userEntity);
        }
    }
}
