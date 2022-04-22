package ru.gb.gbshopthymeleaf.web.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.gb.api.category.dto.CategoryDto;
import ru.gb.api.product.dto.ProductDto;
import ru.gb.gbshopthymeleaf.entity.Product;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


public interface ProductMapper {
    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);
}
