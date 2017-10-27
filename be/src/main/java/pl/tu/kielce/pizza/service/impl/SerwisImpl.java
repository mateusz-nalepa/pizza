package pl.tu.kielce.pizza.service.impl;

import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.service.Serwis;

@Service
public class SerwisImpl implements Serwis {

    @Override
    public void wyswietl() {
        System.out.println("Dupa");
    }
}
