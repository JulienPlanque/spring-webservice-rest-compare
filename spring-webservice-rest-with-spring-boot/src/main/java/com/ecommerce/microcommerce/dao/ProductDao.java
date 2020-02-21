package com.ecommerce.microcommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.microcommerce.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	Product findById(int id);
}