package pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses;

import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.KwakanieInterface;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.LatanieInterface;

public class GumowaKaczka extends Kaczka{

    public GumowaKaczka(LatanieInterface latanieInterface, KwakanieInterface kwakanieInterface) {
        this.latanieInterface = latanieInterface;
        this.kwakanieInterface = kwakanieInterface;
    }
}
