package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.fabryki;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.Phone;

public abstract class FabrykaTelefonow {


    public Phone buyPhone(String type) {
        Phone phone = createPhone(type);

        System.out.println("Sprzedano telefon: " + phone.getNazwa());
        return phone;
    }

    protected abstract Phone createPhone(String type);
}
