package com.swiftacad.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swiftacad.entity.Product;
import com.swiftacad.entity.Shop;
import com.swiftacad.repository.ProductRepository;
import com.swiftacad.repository.ShopRepository;

@Controller
public class ShopController {
	@Autowired
	private ShopRepository shopRepo;
	@Autowired
	private ProductRepository productRepo;
	@RequestMapping(value = "shop", method = RequestMethod.POST)
	public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
		Shop s = shopRepo.save(shop);
		for(Product p : shop.getProducts()) {
			p.setShop(s);
			productRepo.save(p);
		}
//		productRepo.saveAll(s.getProducts());
		return new ResponseEntity<Shop>(HttpStatus.OK);
	}
}
