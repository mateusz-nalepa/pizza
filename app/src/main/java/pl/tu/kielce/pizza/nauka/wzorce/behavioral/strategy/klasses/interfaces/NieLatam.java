package pl.tu.kielce.pizza.nauka.wzorce.behavioral.strategy.klasses.interfaces;

public class NieLatam implements LatanieInterface {
    @Override
    public void lec() {
        System.out.println("Nie umiem latac");
    }
}
