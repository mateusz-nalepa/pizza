package pl.tu.kielce.pizza.common.security.service;

import pl.tu.kielce.pizza.common.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.common.department.dto.FreeUserDto;
import pl.tu.kielce.pizza.common.security.dto.ChangePasswordDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByEmail(String email);

    UserDto saveUser(UserDto user);

    List<FreeManagerDto> freeManagers();

    void changePassword(ChangePasswordDto changePasswordDto);

    List<FreeUserDto> findAllFreeUsers();
}
