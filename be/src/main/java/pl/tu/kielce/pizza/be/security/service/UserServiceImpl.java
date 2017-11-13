package pl.tu.kielce.pizza.be.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.security.repository.user.UserExecutor;
import pl.tu.kielce.pizza.common.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private final UserExecutor userExecutor;

    @Override
    public UserDto findByEmail(String email) {
        return userExecutor.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto user) {
        return userExecutor.save(user);
    }

    @Override
    public List<FreeManagerDto> freeManagers() {
        return userExecutor.freeManagers();
    }

}
