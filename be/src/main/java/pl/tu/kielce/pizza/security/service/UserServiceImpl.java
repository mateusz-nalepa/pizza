package pl.tu.kielce.pizza.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.security.dto.UserDto;
import pl.tu.kielce.pizza.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.security.repository.user.UserExecutor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    UserExecutor userExecutor;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDto findByEmail(String email) {
        return userExecutor.findByEmail(email);
    }

    @Override
    public UserDto saveUser(UserDto user) {
        return userExecutor.save(user);
    }

}
