package com.chinaka.task.mintyn.repository;

import com.chinaka.task.mintyn.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //Page<Order> findAll(Pageable pageable);
    Order findOrderById(Long id);
}
