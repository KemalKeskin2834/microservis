package com.kemalkeskin.product.repository;

import com.kemalkeskin.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository  extends JpaRepository<Product,Integer> {


  @Query("SELECT p.price FROM Product p WHERE p.id = :id ")
    double findByPrice( @Param("id") int id);

}
