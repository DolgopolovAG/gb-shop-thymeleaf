package ru.gb.gbshopthymeleaf.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopthymeleaf.entity.security.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
}
