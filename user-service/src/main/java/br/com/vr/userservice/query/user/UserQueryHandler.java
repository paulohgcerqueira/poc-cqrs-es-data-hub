package br.com.vr.userservice.query.user;

import br.com.vr.userservice.core.user.entities.UserEntity;
import br.com.vr.userservice.core.user.repository.UserRepository;
import br.com.vr.userservice.query.user.rest.dtos.UserResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryHandler {

    private UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    public List<UserResponse> list(ListUsersQuery query) {

        List<UserResponse> usersResponse = new ArrayList<>();
        List<UserEntity> usersEntities =  userRepository.findAll();

        for(UserEntity userEntity: usersEntities) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userEntity, userResponse);
            usersResponse.add(userResponse);
        }

        return usersResponse;

    }
}
