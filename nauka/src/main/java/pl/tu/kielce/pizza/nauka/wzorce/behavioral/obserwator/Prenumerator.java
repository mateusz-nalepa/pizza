package pl.tu.kielce.pizza.nauka.wzorce.behavioral.obserwator;

public interface Prenumerator {

    void mojeDane();
    void update(String info);

    //można by w sumie dać, jaką subskrypcję, bo taki Janusz może mieć wiele prenumerat!
    void usunSubskrybcje();
}
