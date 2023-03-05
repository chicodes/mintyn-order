package com.chinaka.task.mintyn.service;

import com.chinaka.task.mintyn.dto.GenericResponse;
import com.chinaka.task.mintyn.dto.OrderDto;
import com.chinaka.task.mintyn.dto.OrderResponse;

public interface OrderService {
    GenericResponse addOrder(OrderDto order);

    GenericResponse getOrder(String id);

    GenericResponse listOrder(int pageNo, int pageSize);
}
