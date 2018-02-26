package pl.tu.kielce.pizza.common.order.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.order.dto.BoughtItemDto;
import pl.tu.kielce.pizza.common.order.dto.BoughtPizzaDto;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

@Component
@SessionScope
@Getter
@Setter
public class UserContext {



//    @Autowired

    @Autowired
    public UserContext(DepartmentService departmentService) {
        this.departmentService = departmentService;
        this.departmentDto = departmentService.findOne(1L);
    }

    private final DepartmentService departmentService;

    private UserOrderDto userOrderDto = new UserOrderDto();

    private DepartmentDto departmentDto;

    private Double multiplier;

    public void addPizzaToOrder(BoughtPizzaDto boughtPizzaDto) {
        userOrderDto.getBoughtPizzas().add(boughtPizzaDto);
        addPizzaTotalPrice(boughtPizzaDto);
    }


    public void addItemToOrder(BoughtItemDto boughtItemDto) {
        userOrderDto.getBoughtItems().add(boughtItemDto);
        addItemTotalPrice(boughtItemDto);
    }

    public void addItemTotalPrice(BoughtItemDto boughtItemDto) {
        boughtItemDto.increaseQuantity();
        Double newTotalPrice = userOrderDto.getTotalPrice() + boughtItemDto.getPurchasePrice();
        userOrderDto.setTotalPrice(newTotalPrice);
    }

    public void addPizzaTotalPrice(BoughtPizzaDto boughtPizzaDto) {
        boughtPizzaDto.increaseQuantity();
        Double newTotalPrice = userOrderDto.getTotalPrice() + boughtPizzaDto.getPurchasePrice();
        userOrderDto.setTotalPrice(newTotalPrice);
    }


    public boolean isSetDepartment() {
        return departmentDto != null;
    }

    public boolean isNotSetDepartment() {
        return !isSetDepartment();
    }


    public DepartmentDto fetchDepartment() {
        DepartmentDto departmentDto = getDepartmentDto();
        if (departmentDto == null) {

            departmentDto = UserUtils.getDepartment();


            if (departmentDto != null) {
                return departmentDto;
            } else {
                return null;
            }
        } else {
            return departmentDto;
        }
    }
}
