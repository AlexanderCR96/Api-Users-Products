package com.api.mapper;

import com.api.dto.request.ProductRequest;
import com.api.dto.response.ProductResponse;
import com.api.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T10:40:34-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequest dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( dto.getName() );
        product.price( dto.getPrice() );
        product.stock( dto.getStock() );
        product.description( dto.getDescription() );

        return product.build();
    }

    @Override
    public ProductResponse toResponse(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( entity.getId() );
        productResponse.name( entity.getName() );
        productResponse.price( entity.getPrice() );
        productResponse.stock( entity.getStock() );
        productResponse.description( entity.getDescription() );
        productResponse.createdAt( entity.getCreatedAt() );

        return productResponse.build();
    }

    @Override
    public void updateFromDto(ProductRequest dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
        entity.setPrice( dto.getPrice() );
        entity.setStock( dto.getStock() );
        entity.setDescription( dto.getDescription() );
    }
}
