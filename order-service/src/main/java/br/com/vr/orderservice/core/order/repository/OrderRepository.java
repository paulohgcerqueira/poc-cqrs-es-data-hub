package br.com.vr.orderservice.core.order.repository;

import br.com.vr.orderservice.core.order.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findByOrderNumber(String orderNumber);
    List<OrderEntity> findByUserId(String userId);

}
