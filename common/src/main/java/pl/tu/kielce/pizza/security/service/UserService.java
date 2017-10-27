package pl.tu.kielce.pizza.security.service;

import pl.tu.kielce.pizza.security.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);
    UserDto saveUser(UserDto user);

}
