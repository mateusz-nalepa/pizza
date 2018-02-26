package pl.tu.kielce.pizza.fe.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.department.dto.ChooseDepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/client/department")
@RequiredArgsConstructor
public class ClientDepartmentController {


    @Autowired
    private final DepartmentService departmentService;

    @GetMapping("/")
    public String chooseDepartment(Model model) {
        model.addAttribute("chooseDepartmentDto", new ChooseDepartmentDto());
        return "department/choose_department";
    }

    @PostMapping("/")
    public String departmentChoosed(@Valid ChooseDepartmentDto chooseDepartmentDto) {


        departmentService.setDepartmentInContext(chooseDepartmentDto);

        return "redirect:/client/order";
    }


    @GetMapping("/all")
    public String allDepartments(Model model) {
        List<DepartmentDto> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/department_for_users";
    }

    @GetMapping("/deliveryinfo")
    public String deliveryInfo(Model model) {
        return "department/delivery_info";
    }

    @GetMapping("/regulamin")
    public String regulamin(Model model) {
        return "department/regulamin_info";
    }

    @ModelAttribute("departments")
    public List<DepartmentDto> departments() {
        return departmentService.findAll();
    }
}
