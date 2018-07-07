package pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses;

import lombok.experimental.Delegate;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.KwakanieInterface;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.LatanieInterface;

public abstract class Kaczka {

    @Delegate
    LatanieInterface latanieInterface;

    @Delegate
    KwakanieInterface kwakanieInterface;



    public void setLatanieInterface(LatanieInterface latanieInterface) {
        this.latanieInterface = latanieInterface;
    }

    public void setKwakanieInterface(KwakanieInterface kwakanieInterface) {
        this.kwakanieInterface = kwakanieInterface;
    }


}
