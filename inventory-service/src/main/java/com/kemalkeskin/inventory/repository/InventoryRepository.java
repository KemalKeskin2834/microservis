package com.kemalkeskin.inventory.repository;

import com.kemalkeskin.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    boolean existsByProductIdAndQuantityIsGreaterThanEqual(int productId,int quantity);

    Optional<Inventory> findByProductId(int productId);

  /*  @Query("SELECT i.quantity FROM Inventory i WHERE i.ProductId = :productId")
    boolean findQuantityByProductId(@Param("productId") int productId);

   */
}
