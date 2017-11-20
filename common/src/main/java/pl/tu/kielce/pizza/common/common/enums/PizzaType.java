package pl.tu.kielce.pizza.common.common.enums;

import lombok.Getter;

@Getter
public enum PizzaType {
    SMALL(0), MEDIUM(1), LARGE(2);

    int value;

    PizzaType(int i) {
        this.value = i;
    }

    public static PizzaType fromId(int id) {
        for (PizzaType type : PizzaType.values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        throw new RuntimeException("Type of pizza not found!");
    }
}
