package pl.tu.kielce.pizza.nauka.wzorce.behavioral.obserwator;

public interface Wydawca {
    void dodajPrenumaratora(Prenumerator prenumerator);
    void usunPrenumeratora(Prenumerator prenumerator);
    void wyslijInfo();
}
