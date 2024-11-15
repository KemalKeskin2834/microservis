package com.kemalkeskin.product.business.abstracts;

import com.kemalkeskin.product.business.Dtos.CreateProductRequest;
import com.kemalkeskin.product.business.Dtos.ProductResponse;
import com.kemalkeskin.product.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> listProducts();

    ProductResponse getById(int id);

    CreateProductRequest add(CreateProductRequest createProductRequest,int quantity);

    void update(int id,CreateProductRequest createProductRequest);

    void deleteById(int id);

    double giveMePrice(int id);
}
