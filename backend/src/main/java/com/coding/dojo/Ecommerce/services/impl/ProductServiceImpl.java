package com.coding.dojo.Ecommerce.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coding.dojo.Ecommerce.dto.ProductDto;
import com.coding.dojo.Ecommerce.dto.Response;
import com.coding.dojo.Ecommerce.entities.Category;
import com.coding.dojo.Ecommerce.entities.Product;
import com.coding.dojo.Ecommerce.exceptions.NotFoundException;
import com.coding.dojo.Ecommerce.mapper.EntityDtoMapper;
import com.coding.dojo.Ecommerce.repositories.CategoryRepo;
import com.coding.dojo.Ecommerce.repositories.ProductRepo;
import com.coding.dojo.Ecommerce.services.interf.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;
	private final EntityDtoMapper entityDtoMapper;

	@Override
	public Response createProduct(Long categoryId, MultipartFile image, String name, String description,
			BigDecimal price) throws IOException {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));

		Product product = new Product();
		product.setCategory(category);
		product.setPrice(price);
		product.setName(name);
		product.setDescription(description);

		try {
			product.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		productRepo.save(product);

		return Response.builder().status(200).message("Product successfully created").build();
	}

	@Override
	public Response updateProduct(Long productId, Long categoryId, MultipartFile image, String name, String description,
			BigDecimal price) throws IOException {
		Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("Product Not Found"));

		Category category = null;

		if (categoryId != null) {
			category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
		}
		if (image != null && !image.isEmpty()) {
		}

		if (category != null)
			product.setCategory(category);
		if (name != null)
			product.setName(name);
		if (price != null)
			product.setPrice(price);
		if (description != null)
			product.setDescription(description);
		if (image != null)
			try {
				product.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}

		productRepo.save(product);
		return Response.builder().status(200).message("Product updated successfully").build();

	}

	@Override
	public Response deleteProduct(Long productId) {
		Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("Product Not Found"));
		productRepo.delete(product);

		return Response.builder().status(200).message("Product deleted successfully").build();
	}

	@Override
	public Response getProductById(Long productId) {
		Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("Product Not Found"));
		ProductDto productDto = entityDtoMapper.mapProductToDtoBasic(product);

		return Response.builder().status(200).product(productDto).build();
	}

	@Override
	public Response getAllProducts() {
		List<ProductDto> productList = productRepo.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
				.map(entityDtoMapper::mapProductToDtoBasic).collect(Collectors.toList());

		return Response.builder().status(200).productList(productList).build();

	}

	@Override
	public Response getProductsByCategory(Long categoryId) {
		List<Product> products = productRepo.findByCategoryId(categoryId);
		if (products.isEmpty()) {
			throw new NotFoundException("No Products found for this category");
		}
		List<ProductDto> productDtoList = products.stream().map(entityDtoMapper::mapProductToDtoBasic)
				.collect(Collectors.toList());

		return Response.builder().status(200).productList(productDtoList).build();

	}

	@Override
	public Response searchProduct(String searchValue) {
		List<Product> products = productRepo.findByNameContainingOrDescriptionContaining(searchValue, searchValue);

		if (products.isEmpty()) {
			throw new NotFoundException("No Products Found");
		}
		List<ProductDto> productDtoList = products.stream().map(entityDtoMapper::mapProductToDtoBasic)
				.collect(Collectors.toList());

		return Response.builder().status(200).productList(productDtoList).build();
	}
}
