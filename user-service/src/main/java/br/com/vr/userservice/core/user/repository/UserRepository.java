package br.com.vr.userservice.core.user.repository;

import br.com.vr.userservice.core.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByFirstNameOrLastName(String firstName, String lastName);

}
