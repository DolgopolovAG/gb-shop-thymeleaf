package ru.gb.gbshopthymeleaf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbshopthymeleaf.dao.ProductDao;
import ru.gb.gbshopthymeleaf.entity.Product;
import ru.gb.gbapi.common.enums.Status;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDao productDao;

    public Product save(Product product) {
        if (product.getId() != null) {
            Optional<Product> productFromDbOptional = productDao.findById(product.getId());
            if (productFromDbOptional.isPresent()) {
                Product productFromDb = productFromDbOptional.get();
                productFromDb.setTitle(product.getTitle());
                productFromDb.setManufactureDate(product.getManufactureDate());
                productFromDb.setCost(product.getCost());
                productFromDb.setStatus(product.getStatus());
                return productDao.save(productFromDb);
            }
        }
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        //Comparator.comparing(Product::getId).reversed() -- DESC сортировка
        return  productDao.findAll().stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't product with id {}", id);
        }

    }








    public void disableById(Long id) {
        productDao.findById(id).ifPresent(
                p -> {
                    p.setStatus(Status.DISABLED);
                    productDao.save(p);
                }
        );
    }

    public List<Product> findAllActive() {
        return productDao.findAllByStatus(Status.ACTIVE);
    }


    public List<Product> findAllActive(int page, int size) {
        return productDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size));
    }

    public List<Product> findAllActiveSortedById(Sort.Direction direction) {
        return productDao.findAllByStatus(Status.ACTIVE, Sort.by(direction, "id"));
    }

    public List<Product> findAllActiveSortedById(int page, int size, Sort.Direction direction) {
        return productDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size, Sort.by(direction, "id")));
    }

    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        System.out.println(productDao.count());
        // какая-то логика
        return productDao.count();
    }
}
