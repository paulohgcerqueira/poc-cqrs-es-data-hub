package br.com.vr.datahubservice.core.userorder.repository;

import br.com.vr.datahubservice.core.userorder.entities.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, String> {

    List<UserOrderEntity> findByUserId(String userId);
}
