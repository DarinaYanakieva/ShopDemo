package com.swiftacad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findByDescription(String description);
}
