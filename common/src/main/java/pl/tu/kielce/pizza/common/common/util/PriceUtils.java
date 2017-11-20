package pl.tu.kielce.pizza.common.common.util;

import org.apache.commons.math3.util.Precision;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

public class PriceUtils {

    public static void setPriceWithMultiplier(Double price) {
        Double multiplier = UserUtils.getMultiplier();
        price = (multiplier * price) + price;
    }

    public static Double priceWithMultiplier(Double price) {
        Double multiplier = UserUtils.getMultiplier();
        return  (multiplier * price) + price;
    }

    //dodaj metode uniwersalna do podnoszenia cen!

    public static Double mediumPrice(Double price) {

        Double newPrice = (0.15 * price) + price;
        return Precision.round(newPrice, 0);
    }

    public static Double largePrice(Double price) {
        Double newPrice = (0.25 * price) + price;
        return Precision.round(newPrice, 0);
    }

}

