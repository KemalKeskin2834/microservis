package com.kemalkeskin.product.controller;

import com.kemalkeskin.product.business.Dtos.CreateProductRequest;
import com.kemalkeskin.product.business.Dtos.ProductResponse;
import com.kemalkeskin.product.business.abstracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    private ProductService  productService;

    @Autowired
    public ProductControllers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> listProducts(){
        return  productService.listProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable int id){
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductRequest add(@RequestBody CreateProductRequest createProductRequest, @RequestParam int quantity){
        return productService.add(createProductRequest,quantity);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestParam int id,@RequestBody CreateProductRequest createProductRequest){
        this.productService.update(id,createProductRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable int id){
        this.productService.deleteById(id);
    }

    @GetMapping("/price/{id}")
    @ResponseStatus(HttpStatus.OK)
    double giveMePrice(@PathVariable int id){
        return productService.giveMePrice(id);
    }

}
