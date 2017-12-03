package pl.tu.kielce.pizza.common.common.util;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

@Service
@RequiredArgsConstructor
public class NewPriceContextUtils {

    @Autowired
    private final UserContext userContext;

    public Double priceWithMultiplier(Double oldPrice) {

        if (UserUtils.isAdmin()) {
            if (userContext.isSetDepartment()) {
                Double multiplier = userContext.getDepartmentDto().getMultiplier();
                return calculateNewPrice(oldPrice, multiplier);

            } else if (userContext.isNotSetDepartment()) {
                return oldPrice;
            }
        }

        if (UserUtils.isUser() || UserUtils.isManager()) {
            Double multiplier = UserUtils.getMultiplier();
            return calculateNewPrice(oldPrice, multiplier);
        }

        if (UserUtils.isClient()) {
            if (userContext.isSetDepartment()) {
                Double multiplier = userContext.getDepartmentDto().getMultiplier();
                return calculateNewPrice(oldPrice, multiplier);

            } else if (userContext.isNotSetDepartment()) {
                Double multiplier = UserUtils.getMultiplier();
                return calculateNewPrice(oldPrice, multiplier);
            }
        }

        if (UserUtils.isAnonymouse()) {
            Double multiplier = userContext.getDepartmentDto().getMultiplier();
            return calculateNewPrice(oldPrice, multiplier);
        }

        return oldPrice;
    }

    private Double calculateNewPrice(Double oldPrice, Double multiplier) {
        return (multiplier * oldPrice) + oldPrice;
    }

    public static Double mediumPrice(Double price) {

        Double newPrice = (0.15 * price) + price;
        return Precision.round(newPrice, 0);
    }

    public static Double largePrice(Double price) {
        Double newPrice = (0.25 * price) + price;
        return Precision.round(newPrice, 0);
    }


}
