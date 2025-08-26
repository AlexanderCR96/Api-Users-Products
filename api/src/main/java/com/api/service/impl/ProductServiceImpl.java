package com.api.service.impl;


import com.api.dto.request.ProductRequest;
import com.api.dto.response.ProductResponse;
import com.api.exception.ResourceNotFoundException;
import com.api.model.Product;
import com.api.repository.ProductRepository;
import com.api.service.ProductService;
import com.api.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            throw new IllegalArgumentException("Product name already existx");
        }
        Product product = productMapper.toEntity(productRequest);
        Product saveProduct = productRepository.save(product);
        return productMapper.toResponse(saveProduct);
    }

    @Override
    @Transactional
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    @Override
    @Transactional
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        productMapper.updateFromDto(productRequest, existingProduct);
        Product updateProduct = productRepository.save(existingProduct);
        return productMapper.toResponse(updateProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
