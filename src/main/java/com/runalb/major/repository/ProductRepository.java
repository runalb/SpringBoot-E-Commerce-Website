package com.runalb.major.repository;

import com.runalb.major.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory_Id(int id);
}
