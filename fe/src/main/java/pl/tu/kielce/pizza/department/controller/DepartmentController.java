package pl.tu.kielce.pizza.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.department.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager/department")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @GetMapping("{departmentId}")
    public String getDepartment(
            Model model,
            @PathVariable("departmentId") Long departmentId) {

        DepartmentDto departmentDto = departmentService.getById(departmentId);

        model.addAttribute("departmentDto", departmentDto);
        return "department/show_department";
    }

    @GetMapping("/")
    public String departmentDefinition(Model model) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setActive(true);
        model.addAttribute("departmentDto", departmentDto);
        return "department/department_definition";
    }

    @PostMapping("/")
    public String addNewDepartment(@Valid DepartmentDto departmentDto, BindingResult bindingResult, Model model) {

        departmentDto.setMultiplier(departmentDto.getMultiplier() / 100);

        departmentDto = departmentService.add(departmentDto);
        System.out.println("hodoreczek");

        return "redirect:/manager/department/" + departmentDto.getId();
    }

    @GetMapping("/all")
    public String allDepartments(Model model) {
        List<DepartmentDto> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/department_search";
    }

    @ModelAttribute("freeManagers")
    public List<FreeManagerDto> freeManagers() {
        return departmentService.freeManagers();
    }
}
