package com.product.service;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.model.Product;
import com.product.model.ServiceResponse;

@RequestMapping("api/product")
public interface ProductService {

	@GetMapping(value = "/getAllProducts")
	@ResponseBody
	ServiceResponse<Collection<Product>> getAllProducts();

	@GetMapping(value = "/getByProdId/{prodId}")
	@ResponseBody
	ServiceResponse<Product> getProductById(@PathVariable String prodId);

	@PostMapping(value = "/addproduct")
	@ResponseBody
	ServiceResponse<Product> addProduct(@RequestBody Product prod);

	@PutMapping(value = "/updateProd")
	@ResponseBody
	ServiceResponse<Product> updateProduct(@RequestBody Product product);
}
