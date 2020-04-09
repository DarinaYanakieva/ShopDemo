package com.swiftacad.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swiftacad.entity.Product;
import com.swiftacad.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value="product", method = RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		System.out.println(productRepository);
		productRepository.save(product);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
//	product/{id}
	@RequestMapping(value = "product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
		Optional<Product> optional = productRepository.findById(id);
		
		if( optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach( p -> products.add(p));
		
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	@RequestMapping(value = "product/description/{description}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findByDescription(@PathVariable("description") String description){
		List<Product> products = productRepository.findByDescription(description);
		
		if( products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="product", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		productRepository.save(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="product/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id ) {
		productRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	
}
