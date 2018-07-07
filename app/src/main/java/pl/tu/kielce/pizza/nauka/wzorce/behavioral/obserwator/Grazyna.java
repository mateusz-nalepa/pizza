package pl.tu.kielce.pizza.nauka.wzorce.behavioral.obserwator;

public class Grazyna implements Prenumerator {

    private Wydawca wydawca;

    public Grazyna(Wydawca wydawca) {
        this.wydawca = wydawca;
        this.wydawca.dodajPrenumaratora(this);
    }

    @Override
    public void mojeDane() {
        System.out.println("Grazyna");
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
