package com.kemalkeskin.product.business.businessrules;

import com.kemalkeskin.product.core.exception.BusinessException;
import com.kemalkeskin.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductRules {

    private ProductRepository productRepository;

    public ProductRules(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public void exitisById(int id){
        if(!productRepository.findById(id).isPresent()){
            throw new BusinessException("don't found id "+id);
        }
    }
}
