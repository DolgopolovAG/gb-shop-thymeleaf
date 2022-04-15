package ru.gb.gbshopthymeleaf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbshopthymeleaf.dao.CartDao;
import ru.gb.gbshopthymeleaf.entity.Cart;
import ru.gb.gbshopthymeleaf.entity.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;

    public Cart save(Cart cart) {
        return cartDao.save(cart);
    }

    @Transactional(readOnly = true)
    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartDao.findAll();
    }

}
