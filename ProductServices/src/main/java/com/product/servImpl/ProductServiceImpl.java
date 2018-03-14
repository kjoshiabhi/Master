package com.product.servImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.model.ServiceResponse;
import com.product.repo.ProductRepository;
import com.product.service.ProductService;

@RestController
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository productRepo;

	@Override
	public ServiceResponse<Collection<Product>> getAllProducts() {

		ServiceResponse<Collection<Product>> response = new ServiceResponse<>();

		try {
			Collection<Product> products = productRepo.findAll();
			response.setData(products);
		} catch (Throwable th) {
			response.setError("error in  getting products");
		}

		return response;
	}

	@Override
	public ServiceResponse<Product> getProductById(@PathVariable String prodId) {

		ServiceResponse<Product> response = new ServiceResponse<>();

		try {
			Product productById = productRepo.findByProdId(prodId);
			if (productById == null) {
				response.setError("Unable to get product");
			} else {
				response.setData(productById);
			}

		} catch (Throwable th) {
			response.setError("error in  getting product");
		}

		return response;
	}

	@Override
	public ServiceResponse<Product> addProduct(@RequestBody Product prod) {

		ServiceResponse<Product> response = new ServiceResponse<>();
		try {

			Product prodExists = productRepo.findByProdId(prod.getProdId());
			if (prodExists == null) {
				Product addprod = productRepo.save(prod);
				response.setData(addprod);
			} else {
				response.setError("Product Already exists");
			}
		} catch (Throwable th) {
			response.setError("error in adding product");
		}

		return response;
	}

	@Override
	public ServiceResponse<Product> updateProduct(@RequestBody Product product) {

		ServiceResponse<Product> response = new ServiceResponse<>();
		try {
			Product prodExists = productRepo.findByProdId(product.getProdId());
			if (prodExists != null) {

				prodExists.setProdDesc(product.getProdDesc());
				prodExists.setProdName(product.getProdName());
				prodExists.setProdPrice(product.getProdPrice());
				prodExists.setProdQuant(product.getProdQuant());
				Product updatedprod = productRepo.save(prodExists);
				response.setData(updatedprod);
			} else {
				response.setError("Product do not  exists");
			}
		} catch (Throwable th) {
			response.setError("error in updating product");
		}

		return response;
	}

}
