package pl.tu.kielce.pizza.be.order.model.jpa.enums.orderstatus;

public enum OrderStatus {

    NEW(1), IN_PROGRESS(2);

    private Integer value;

    OrderStatus(int value) {
        this.value = value;
    }
}
