package ru.gb.gbshopthymeleaf.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopthymeleaf.entity.Product;
import ru.gb.gbshopthymeleaf.entity.enums.Status;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
    List<Product> findAllByTitleContaining(String title);

    List<Product> findAllByStatus(Status status);
    List<Product> findAllByStatus(Status status, Pageable pageable);
    List<Product> findAllByStatus(Status status, Sort sort);
}
