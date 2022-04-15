package ru.gb.gbshopthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopthymeleaf.entity.Cart;
import ru.gb.gbshopthymeleaf.entity.enums.Status;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Long>{

    List<Cart> findAllByStatus(Status status);
}
