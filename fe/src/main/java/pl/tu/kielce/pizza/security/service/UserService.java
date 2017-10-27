package pl.tu.kielce.pizza.security.service;

import pl.tu.kielce.pizza.dto.user.UserDto;

public interface UserService {

    UserDto saveUser(UserDto user);
}
