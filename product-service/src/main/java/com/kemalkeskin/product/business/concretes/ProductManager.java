package com.kemalkeskin.product.business.concretes;

import com.kemalkeskin.product.business.Dtos.CreateProductRequest;
import com.kemalkeskin.product.business.Dtos.ProductResponse;
import com.kemalkeskin.product.business.abstracts.ProductService;
import com.kemalkeskin.product.business.businessrules.ProductRules;
import com.kemalkeskin.product.client.InventoryClient;
import com.kemalkeskin.product.core.mapper.ModelMapperService;
import com.kemalkeskin.product.model.Product;
import com.kemalkeskin.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;
    private ProductRules productRules;
    private InventoryClient inventoryClient;

    @Autowired
    public ProductManager(ProductRepository productRepository,ModelMapperService modelMapperService,ProductRules productRules,InventoryClient inventoryClient) {
        this.productRepository = productRepository;
        this.modelMapperService=modelMapperService;
        this.productRules=productRules;
        this.inventoryClient=inventoryClient;
    }

    @Override
    public List<ProductResponse> listProducts() {
        List<Product>products= productRepository.findAll();
        List<ProductResponse>productResponses=products.stream().map(product ->modelMapperService.forResponse().map(product,ProductResponse.class)).collect(Collectors.toList());
        return productResponses;
    }

    @Override
    public ProductResponse getById(int id) {
        this.productRules.exitisById(id);
        Product product=productRepository.findById(id).get();
        return modelMapperService.forResponse().map(product,ProductResponse.class);
    }

    @Override
    public CreateProductRequest add(CreateProductRequest createProductRequest,int quantity) {

     /*   Product product=new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        productRepository.save(product);*/

        Product product=modelMapperService.forRequest().map(createProductRequest,Product.class);
        Product productSave=this.productRepository.save(product);
        inventoryClient.createInventory(product.getId(),quantity);

        return createProductRequest;

    }

    @Override
    public void update(int id, CreateProductRequest createProductRequest) {
        this.productRules.exitisById(id);
        Optional<Product> product=productRepository.findById(id);
        Product productUpdate=product.get();
        productUpdate.setName(createProductRequest.getName());
        productUpdate.setDescription(createProductRequest.getDescription());
        productUpdate.setPrice(createProductRequest.getPrice());
        productRepository.save(productUpdate);

    }

    @Override
    public void deleteById(int id) {
        this.productRules.exitisById(id);
        productRepository.deleteById(id);

    }

    @Override
    public double giveMePrice(int id) {
        return productRepository.findByPrice(id);
    }
}
