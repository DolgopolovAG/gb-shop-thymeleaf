insert into account_user(username, password, firstname, lastname, account_non_expired, account_non_locked, credentials_non_expired, enabled)
values ('user', '$2a$10$o76vT7be2Wlf5COCxdpu.eOhS56trBcLCN.NqSDW00hGz9SSCzA6u', 'Иван', 'Петров', true, true, true,
        true),
       ('admin', '$2a$10$AbUwMreMZ1y7JDKKGhshve6DazhHb4DilCfif8cxNpHAn0u2D8aYO', 'Владимир', 'Иванов', true, true, true,
        true);

insert into authority (permission)
values ('product.create'),
       ('product.read'),
       ('product.update'),
       ('product.delete');

insert into account_role (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into role_authority (authority_id, role_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (2, 2);

insert into user_role (user_id, role_id)
values (1, 2),
       (2, 1);
