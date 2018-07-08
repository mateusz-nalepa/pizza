package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony;

public abstract class Phone {

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    private String nazwa;
}
