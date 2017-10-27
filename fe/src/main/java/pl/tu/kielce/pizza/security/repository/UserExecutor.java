package pl.tu.kielce.pizza.security.repository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.dto.user.UserDto;
import pl.tu.kielce.pizza.security.mapper.UserMapper;
import pl.tu.kielce.pizza.security.model.jpa.User;

@Repository
@RequiredArgsConstructor
public class UserExecutor {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Delegate
    @Autowired
    private UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user = userRepository.save(user);
        return userMapper.entityToDto(user);
    }
}
