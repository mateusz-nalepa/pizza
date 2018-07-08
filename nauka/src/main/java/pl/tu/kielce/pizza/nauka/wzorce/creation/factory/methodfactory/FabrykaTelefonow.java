package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory;

public abstract class FabrykaTelefonow {


    Phone buyPhone(String type) {
        Phone phone = createPhone(type);

        System.out.println("Sprzedano telefon: " + phone.getNazwa());
        return phone;
    }

    protected abstract Phone createPhone(String type);
}
