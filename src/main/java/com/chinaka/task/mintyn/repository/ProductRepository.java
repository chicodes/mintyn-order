package com.chinaka.task.mintyn.repository;

import com.chinaka.task.mintyn.model.Order;
import com.chinaka.task.mintyn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Page<Order> findAll(Pageable pageable);
    Product findProductById(Long id);
}
