package pl.tu.kielce.pizza.fe.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.security.service.UserService;

@ControllerAdvice
@RequiredArgsConstructor
public class HomePageControllerAdvice {

    @Autowired
    private final UserContext userContext;

    @Autowired
    private final UserService userService;

    @ModelAttribute
    public void globalAttributes(Model model) {

        String departmentName;
        DepartmentDto departmentDto = userContext.fetchDepartment();
        if (departmentDto == null) {
            departmentName = "Department is empty! Ask";
        } else {
            departmentName = departmentDto.getLabel();
        }
        model.addAttribute("msg", departmentName);
    }

    @ModelAttribute
    public void avatarLocation(Model model) {
        String avatarLocation = userService.fetchAvatarLocation();
        model.addAttribute("avatarLocation", avatarLocation);
    }

}
