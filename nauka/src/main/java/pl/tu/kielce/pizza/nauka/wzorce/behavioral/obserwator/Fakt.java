package pl.tu.kielce.pizza.nauka.wzorce.behavioral.obserwator;

import java.util.ArrayList;
import java.util.List;

public class Fakt implements Wydawca {

    private List<Prenumerator> prenumerators = new ArrayList<>();
    private String najnowszyPost;

    public void opublikujPost(String post) {
        najnowszyPost = post;
        wyslijInfo();
    }

    @Override
    public void dodajPrenumaratora(Prenumerator prenumerator) {
        this.prenumerators.add(prenumerator);
    }

    @Override
    public void usunPrenumeratora(Prenumerator prenumerator) {
        this.prenumerators.remove(prenumerator);
    }

    @Override
    public void wyslijInfo() {
        prenumerators
                .forEach(prenumerator -> {
                    System.out.println("-----------------------");
                    prenumerator.mojeDane();
                    prenumerator.update(najnowszyPost);
                });
    }



}
