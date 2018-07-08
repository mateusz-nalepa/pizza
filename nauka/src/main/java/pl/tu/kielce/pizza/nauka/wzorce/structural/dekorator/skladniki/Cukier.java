package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.skladniki;

import pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.napoje.Napoj;

import java.math.BigDecimal;

public class Cukier extends SkladnikDekorator {

    public Cukier(Napoj napoj) {
        this.napoj = napoj;
    }

    @Override
    public String getOpis() {
        return napoj.getOpis() + " cukier";
    }

    @Override
    public BigDecimal koszt() {
        return napoj.koszt().add(new BigDecimal("0.15"));
    }
}
