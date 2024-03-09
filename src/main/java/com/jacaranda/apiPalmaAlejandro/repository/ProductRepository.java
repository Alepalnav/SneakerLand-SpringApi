package com.jacaranda.apiPalmaAlejandro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.apiPalmaAlejandro.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Page<Product> findByRemove(int remove, Pageable pageable);
	
	Page<Product> findByBrand(String brand, Pageable pageable);
	
	Page<Product> findByNameContainsAndRemove(String name, int remove, Pageable pageable);


}
