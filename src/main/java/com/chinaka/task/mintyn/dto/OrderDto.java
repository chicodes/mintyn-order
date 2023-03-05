package com.chinaka.task.mintyn.dto;

import com.chinaka.task.mintyn.util.ProductStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderDto {


    @NotNull(message="quantity not be empty" , groups = OrderDto.Validation.class)
    @NotBlank(message="quantity can not be empty" , groups = OrderDto.Validation.class)
    @NotEmpty(message="quantity can not be empty" , groups = OrderDto.Validation.class)
    private String productId;

    @NotNull(message="customerName not be empty" , groups = OrderDto.Validation.class)
    @NotBlank(message="customerName can not be empty" , groups = OrderDto.Validation.class)
    @NotEmpty(message="customerName can not be empty" , groups = OrderDto.Validation.class)
    private String customerName;

    @NotNull(message="customerPhone not be empty" , groups = OrderDto.Validation.class)
    @NotBlank(message="customerPhone can not be empty" , groups = OrderDto.Validation.class)
    @NotEmpty(message="customerPhone can not be empty" , groups = OrderDto.Validation.class)
    private String customerPhone;

    @NotNull(message="customer Address not be empty" , groups = OrderDto.Validation.class)
    @NotBlank(message="customer Address can not be empty" , groups = OrderDto.Validation.class)
    @NotEmpty(message="customer Address can not be empty" , groups = OrderDto.Validation.class)
    private String customerAddress;

    @NotNull(message="quantity not be empty" , groups = OrderDto.Validation.class)
    @NotBlank(message="quantity can not be empty" , groups = OrderDto.Validation.class)
    @NotEmpty(message="quantity can not be empty" , groups = OrderDto.Validation.class)
    private String quantity;

    @NotNull(message="price not be empty" , groups = OrderDto.Validation.class)
    @NotBlank(message="price can not be empty" , groups = OrderDto.Validation.class)
    @NotEmpty(message="price can not be empty" , groups = OrderDto.Validation.class)
    private String price;

    public interface Validation{}
}
