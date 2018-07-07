package pl.tu.kielce.pizza.fe.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.messages.MessageSourceAccessor;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.security.service.UserService;

import java.util.TimeZone;

@ControllerAdvice
@RequiredArgsConstructor
public class HomePageControllerAdvice {

    @Autowired
    private final UserContext userContext;

    @Autowired
    private final UserService userService;

    @Autowired
    private final MessageSourceAccessor messageSourceAccessor;

    @ModelAttribute
    public void globalAttributes(Model model) {

        String departmentName;
        String workHours;
        DepartmentDto departmentDto = userContext.fetchDepartment();
        if (departmentDto == null) {
            departmentName = messageSourceAccessor.getMessage("department.actualChoosen");
            workHours = "";
        } else {
            departmentName = departmentDto.getLabel();
            workHours = departmentDto.workHours();
        }
        model.addAttribute("departmentLabel", departmentName);
        model.addAttribute("workHours", workHours);
        model.addAttribute("asd", TimeZone.getDefault());
    }



    @ModelAttribute
    public void avatarLocation(Model model) {
        String avatarLocation = userService.fetchAvatarLocation();
        model.addAttribute("avatarLocation", avatarLocation);
    }

}
