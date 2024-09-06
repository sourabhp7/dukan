package com.omniverse.dukan.service.product;

import java.util.List;
import com.omniverse.dukan.model.Product;
import com.omniverse.dukan.request.AddProductRequest;
import com.omniverse.dukan.request.ProductUpdateRequest;


public interface IproductService {

	Product addProduct(AddProductRequest request);

	Product getProductById(Long id);

	void deleteProductById(Long id);

	Product updateProductByid(ProductUpdateRequest product, Long ProductId);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByBrand(String brand);

	List<Product> getProductsByCategoryAndBrand(String category, String brand);

	List<Product> getProductsByName(String name);

	List<Product> getProductsByBrandAndName(String category, String name);

	Long counProductsByBrandAndName(String brand, String name);

	

}
