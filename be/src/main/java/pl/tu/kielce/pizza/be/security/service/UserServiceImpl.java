package pl.tu.kielce.pizza.be.security.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.tu.kielce.pizza.be.security.repository.user.UserExecutor;
import pl.tu.kielce.pizza.common.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.common.department.dto.FreeUserDto;
import pl.tu.kielce.pizza.common.security.dto.ChangePasswordDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private final UserExecutor userExecutor;

    @Autowired
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public List<FreeUserDto> findAllFreeUsers() {
        return userExecutor.freeUsers();
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordDto changePasswordDto) {
        String encodedNewPassword = passwordEncoder.encode(changePasswordDto.getNewPassword());
        changePasswordDto.setNewPassword(encodedNewPassword);
        userExecutor.changePassword(changePasswordDto);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void storeAvatar(MultipartFile multipartFile) {

        String fileName = UUID.randomUUID().toString();
        String newFileName = fileName + multipartFile.getOriginalFilename();
        String pathToSaveFile = "D:/pizza/images/users/" + newFileName;
        File file = new File(pathToSaveFile);
        userExecutor.updateUserAvatar(UserUtils.getUserId(), "/dyskd/" + newFileName);
        multipartFile.transferTo(file);
    }

    @Override
    public String fetchAvatarLocation() {
        String s = userExecutor.fetchAvatarLocation();

        if (s == null) {
            return "/images/default_avatar.png";
        }

        return s;
    }

    @Override
    public List<UserDto> findAllActive() {

        return userExecutor.findAllActive();
    }

}
