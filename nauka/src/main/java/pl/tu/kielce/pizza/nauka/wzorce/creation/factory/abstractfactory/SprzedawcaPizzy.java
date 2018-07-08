package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze.Pizza;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizzerie.AmerykanskaPizzeria;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizzerie.WloskaPizzeria;

public class SprzedawcaPizzy {

    public static void main(String[] args) {
        AmerykanskaPizzeria amerykanskaPizzeria = new AmerykanskaPizzeria();

        Pizza amerykanskaSerowa = amerykanskaPizzeria.zamowPizze("serowa");
        System.out.println(amerykanskaSerowa);

        WloskaPizzeria wloskaPizzeria = new WloskaPizzeria();

        Pizza wloskaSerowa = wloskaPizzeria.zamowPizze("serowa");
        System.out.println(wloskaSerowa);
    }



}
