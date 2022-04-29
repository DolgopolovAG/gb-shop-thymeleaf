package ru.gb.gbshopthymeleaf.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.api.security.dto.UserDto;

@FeignClient(url = "localhost:8081/external/api/v1/user", value = "userGateway")
public interface  UserGateway {

    @GetMapping(value = "/{userId}", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> getUser(@PathVariable("userId") Long id);

    @PostMapping( produces = "application/json;charset=UTF-8")
    ResponseEntity<?> handlePost(@Validated @RequestBody UserDto userDto);

}
