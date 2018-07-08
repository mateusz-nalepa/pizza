package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator;

import pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.napoje.Herbata;
import pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.napoje.Napoj;
import pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.skladniki.Cukier;
import pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.skladniki.Mleczko;

public class StarCafe {


    public static void main(String[] args) {
        Napoj herbata = new Herbata();

        herbata = new Cukier(herbata);
        herbata = new Cukier(herbata);
        herbata = new Mleczko(herbata);

        System.out.println(herbata.getOpis());
        System.out.println(herbata.koszt());


    }
}
