package pl.tu.kielce.pizza.be.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.common.mapper.CommonMapper;
import pl.tu.kielce.pizza.be.security.model.jpa.User;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final RoleMapper roleMapper;

    @Autowired
    private final CommonMapper commonMapper;

    public User dtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setAvatarLocation(dto.getAvatarLocation());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setAccountStatus(dto.getAccountStatus());
        entity.activate();
        entity.setAddress(commonMapper.addressDtoToEntity(dto.getAddressDto()));
        entity.setMainRoleType(dto.getMainRoleType());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }

    public UserDto entityToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setAvatarLocation(user.getAvatarLocation());
        userDto.setMainRoleType(user.getMainRoleType());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setId(user.getId());
        userDto.setAccountStatus(user.getAccountStatus());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(roleMapper.extractRoles(user));
        userDto.setAddressDto(commonMapper.addressEntityToDto(user.getAddress()));

        return commonMapper.baseEntityToDto(user, userDto);
    }

}
