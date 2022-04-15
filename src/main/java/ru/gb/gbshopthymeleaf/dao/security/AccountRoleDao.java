package ru.gb.gbshopthymeleaf.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopthymeleaf.entity.security.AccountRole;

public interface AccountRoleDao extends JpaRepository<AccountRole, Long> {
}
