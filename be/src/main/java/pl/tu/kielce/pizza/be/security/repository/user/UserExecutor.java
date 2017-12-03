package pl.tu.kielce.pizza.be.security.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.security.mapper.UserMapper;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;
import pl.tu.kielce.pizza.be.security.model.jpa.User;
import pl.tu.kielce.pizza.be.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.common.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.common.department.dto.FreeUserDto;
import pl.tu.kielce.pizza.common.queryHandler.NativeResultQuerySetHandler;
import pl.tu.kielce.pizza.common.security.dto.AccountStatus;
import pl.tu.kielce.pizza.common.security.dto.ChangePasswordDto;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserExecutor {

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);

        if (UserUtils.isAuthEmpty() && !UserUtils.isAnonymouse()) {
            user.setRoles(fetchRoles(userDto));
        }

        if (!UserUtils.isAnonymouse()) {
            user.setRoles(fetchRoles(userDto));
        } else {
            user.setRoles(fetchClientRole());
        }

        user = userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    private Set<Role> fetchRoles(UserDto userDto) {

        List<Long> rolesIds = userDto
                .getRoles()
                .stream()
                .filter(RoleDto::isSelected)
                .map(RoleDto::getId)
                .collect(Collectors.toList());

        return roleRepository.findByRolesIds(rolesIds);
    }

    private Set<Role> fetchClientRole() {
        Set<Role> roles = new HashSet<>();
        Role client = roleRepository.findByRole("CLIENT");
        roles.add(client);
        return roles;
    }

    public UserDto findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .map(userMapper::entityToDto)
                .orElse(null);
    }

    public List<FreeManagerDto> freeManagers() {
        return NativeResultQuerySetHandler.resultList(userRepository.findFreeManagers(), FreeManagerDto.class);
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        Optional<User> byEmail = userRepository
                .findByEmail(UserUtils.getUserEmail());

        User user;
        if (byEmail.isPresent()) {
            user = byEmail.get();
        } else {
            //TODO popraw to!
            throw new RuntimeException("WYWALILEM SIE");
        }

        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setPassword(changePasswordDto.getNewPassword());
        userRepository.save(user);
    }

    public List<FreeUserDto> freeUsers() {
        return NativeResultQuerySetHandler.resultList(userRepository.findFreeUsers(), FreeUserDto.class);
    }
}
