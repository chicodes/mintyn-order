package com.chinaka.task.mintyn.controller;


import com.chinaka.task.mintyn.dto.GenericResponse;
import com.chinaka.task.mintyn.dto.OrderDto;
import com.chinaka.task.mintyn.service.OrderService;
import com.chinaka.task.mintyn.dto.OrderResponse;
import com.chinaka.task.mintyn.util.MappingHelper;
import com.chinaka.task.mintyn.util.ResponseHelper;
import com.chinaka.task.mintyn.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(Constants.ORDER_BASE_URL+"/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<GenericResponse> addOrder(@RequestBody @Validated(OrderDto.Validation.class) OrderDto request) {

        GenericResponse resp = orderService.addOrder(request);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<GenericResponse> getOrder(@PathVariable String id) {

        GenericResponse resp = orderService.getOrder(id);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse> listOrder(@RequestParam (defaultValue = "0")int pageNo, @RequestParam(defaultValue = "10") int pageSize) {

        GenericResponse resp = orderService.listOrder(pageNo, pageSize);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
}
