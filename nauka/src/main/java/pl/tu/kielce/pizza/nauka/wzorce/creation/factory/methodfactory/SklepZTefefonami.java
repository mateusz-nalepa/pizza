package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.fabryki.FabrykaAndroidow;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.Phone;

public class SklepZTefefonami {

    public static void main(String[] args) {
        FabrykaAndroidow fabrykaAndroidow = new FabrykaAndroidow();
        Phone xiaomi = fabrykaAndroidow.buyPhone("xiaomi");

    }
}
