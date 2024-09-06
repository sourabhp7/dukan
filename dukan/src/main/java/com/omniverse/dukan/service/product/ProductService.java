package com.omniverse.dukan.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.omniverse.dukan.exceptions.ProductNotFoundException;
import com.omniverse.dukan.model.Category;
import com.omniverse.dukan.model.Product;
import com.omniverse.dukan.repository.CategoryRepository;
import com.omniverse.dukan.repository.ProductRepository;
import com.omniverse.dukan.request.AddProductRequest;
import com.omniverse.dukan.request.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IproductService {

	private final ProductRepository productRepository;

	private final CategoryRepository categoryRepository;

	@Override
	public Product addProduct(AddProductRequest request) {
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
				.orElseGet(() -> {
					Category newCategory = new Category(request.getCategory().getName());
					return categoryRepository.save(newCategory);
				});
		request.setCategory(category);
		return productRepository.save(createProduct(request, category));
	}

	private Product createProduct(AddProductRequest request, Category category) {
		return new Product(request.getName(), request.getBrand(), request.getPrice(), request.getInventory(),
				request.getDescription(), category);
	}

	@Override
	public Product getProductById(Long id) {

		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
			throw new ProductNotFoundException("Product Not found");
		});

	}

	@Override
	public Product updateProductByid(ProductUpdateRequest product, Long ProductId) {
		return productRepository.findById(ProductId)
				.map(existingProduct -> updateExistingProduct(existingProduct, product)).map(productRepository::save)
				.orElseThrow(() -> new ProductNotFoundException("product not found"));

	}

	private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setDescription(request.getDescription());

		Category category = categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setCategory(category);
		return existingProduct;
	}

	@Override
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {

		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {

		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {

		return productRepository.findByCategoryNameAndBrand(category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {

		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		return productRepository.findbyBrandAndName(brand, name);
	}

	@Override
	public Long counProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.countByBrandAndName(brand, name);
	}

}
