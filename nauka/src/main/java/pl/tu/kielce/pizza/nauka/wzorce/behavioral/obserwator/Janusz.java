package pl.tu.kielce.pizza.nauka.wzorce.behavioral.obserwator;

public class Janusz implements Prenumerator {

    Wydawca wydawca;

    public Janusz(Wydawca wydawca) {
        this.wydawca = wydawca;
        this.wydawca.dodajPrenumaratora(this);

    }

    @Override
    public void mojeDane() {
        System.out.println("Janusz");
    }

    @Override
    public void update(String info) {
        System.out.println(info);
    }

    @Override
    public void usunSubskrybcje() {
        this.wydawca.usunPrenumeratora(this);
    }
}
