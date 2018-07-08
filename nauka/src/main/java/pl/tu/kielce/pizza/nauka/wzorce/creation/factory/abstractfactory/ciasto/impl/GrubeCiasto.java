package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.impl;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.Ciasto;

public class GrubeCiasto implements Ciasto {
    @Override
    public void wyswietlNazwe() {
        System.out.println("Grube ciasto");
    }

    @Override
    public String toString() {
        return "GrubeCiasto{}";
    }
}
