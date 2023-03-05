package com.chinaka.task.mintyn.service;

import com.chinaka.task.mintyn.dto.ProductDto;
import com.chinaka.task.mintyn.dto.GenericResponse;

public interface ProductService {

    GenericResponse addProduct(ProductDto request);

    GenericResponse getProduct(String id);

    GenericResponse listProduct(int pageNo, int pageSize);

    GenericResponse updateProduct(String id, ProductDto request);

    GenericResponse deleteProduct(String id);

}
