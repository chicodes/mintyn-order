package com.chinaka.task.mintyn.service;

import com.chinaka.task.mintyn.dto.GenericResponse;
import com.chinaka.task.mintyn.model.Order;
import com.chinaka.task.mintyn.dto.OrderDto;
import com.chinaka.task.mintyn.dto.OrderResponse;
import com.chinaka.task.mintyn.dto.ResponseData;
import com.chinaka.task.mintyn.model.Product;
import com.chinaka.task.mintyn.repository.OrderRepository;
import com.chinaka.task.mintyn.repository.ProductRepository;
import com.chinaka.task.mintyn.util.MappingHelper;
import com.chinaka.task.mintyn.util.ResponseHelper;
import com.chinaka.task.mintyn.util.ProductStatus;
import com.chinaka.task.mintyn.validation.OrderExistException;
import com.chinaka.task.mintyn.util.Constants;
import com.chinaka.task.mintyn.validation.ProductExistException;
import jdk.jshell.spi.ExecutionControlProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static com.chinaka.task.mintyn.util.Constants.*;
import static com.chinaka.task.mintyn.util.Constants.SUCCESS;
import static com.chinaka.task.mintyn.util.Utility.getCurrentDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ResponseHelper responseHelper;

    @Override
    public GenericResponse addOrder(OrderDto request){

        try {
            Product getOrder = productRepository.findProductById(Long.valueOf(request.getProductId()));

            if(Objects.isNull(getOrder))
                throw new ProductExistException("Product Not found");

//            int total = Integer.valueOf(String.valueOf(getOrder.getPrice())) * Integer.valueOf(request.getQuantity());
            BigDecimal total = getOrder.getPrice().multiply(new BigDecimal(request.getQuantity()));
            Order order = new Order();
            log.info("setting order values");
            order.setProductId(request.getProductId());
            order.setCustomerName(request.getCustomerName());
            order.setCustomerPhone(request.getCustomerPhone());
            order.setCustomerAddress(request.getCustomerAddress());
            order.setPrice(getOrder.getPrice());
            order.setQuantity(Integer.valueOf(request.getQuantity()));
            order.setTotal(total);
            order.setDateCreated(getCurrentDate());
            log.info("inserting order, {}", order);
            Order respBody = orderRepository.save(order);
            return responseHelper.getResponse(Constants.SUCCESS_CODE, Constants.SUCCESS, respBody, HttpStatus.CREATED);
        }
        catch (Exception e){
            return responseHelper.getResponse(FAILED_CODE, e.getMessage(), "", HttpStatus.EXPECTATION_FAILED);
        }
    }


    @Cacheable(cacheNames = {"orderCache"}, key = "#id")
    @Override
    public GenericResponse getOrder(String id){
        try {
            log.info("getting order with Id {}", id);
            Order result = orderRepository.findOrderById(Long.valueOf(id));
            if(Objects.isNull(result)){
                throw new OrderExistException(NOT_FOUND);
            }
            log.info("response body:  {}", result);
            return responseHelper.getResponse(SUCCESS_CODE, SUCCESS, result, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return responseHelper.getResponse(FAILED_CODE, e.getMessage(), "", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public GenericResponse listOrder(int pageNo, int pageSize){
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            log.info("getting all order");
            Page<Order> result = orderRepository.findAll(paging);
            log.info("{}", result);
            if (result.isEmpty()) {
                return responseHelper.getResponse(NOT_FOUND_CODE, ORDER_NOT_FOUND, null, HttpStatus.EXPECTATION_FAILED);
            }
            return responseHelper.getResponse(SUCCESS_CODE, SUCCESS, result, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return responseHelper.getResponse(FAILED_CODE, e.getMessage(), "", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
