package pl.tu.kielce.pizza.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.dto.user.UserDto;
import pl.tu.kielce.pizza.security.model.jpa.User;
import pl.tu.kielce.pizza.security.repository.RoleRepository;
import pl.tu.kielce.pizza.security.repository.UserExecutor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    UserExecutor userExecutor;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByEmail(String email) {
        return userExecutor.findByEmail(email);
    }

    @Override
    public UserDto saveUser(UserDto user) {
        return userExecutor.save(user);
    }

}
