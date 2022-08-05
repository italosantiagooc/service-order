package br.com.serviceordersystem.repositories;

import br.com.serviceordersystem.models.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {
}
