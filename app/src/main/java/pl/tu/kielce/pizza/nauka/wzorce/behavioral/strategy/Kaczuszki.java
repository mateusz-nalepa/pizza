package pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy;

import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.DzikaKaczka;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.GumowaKaczka;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.Kaczka;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.KwakamNormalnie;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.MamSkrzydlaToLatam;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.NieLatam;
import pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces.Piszcze;

public class Kaczuszki {

    public static void main(String[] args) {
        Kaczka dzikaKaczka = new DzikaKaczka(new MamSkrzydlaToLatam(), new KwakamNormalnie());

        dzikaKaczka.kwacz();
        dzikaKaczka.lec();

        Kaczka gumowaKaczka = new GumowaKaczka(new NieLatam(), new Piszcze());

        gumowaKaczka.kwacz();
        gumowaKaczka.lec();

        Kaczka zmiennaKaczka = new DzikaKaczka(new MamSkrzydlaToLatam(), new KwakamNormalnie());

        zmiennaKaczka.kwacz();
        zmiennaKaczka.lec();

        zmiennaKaczka.setKwakanieInterface(new Piszcze());

        zmiennaKaczka.kwacz();
    }
}
