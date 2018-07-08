package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory;

public class SklepZTefefonami {

    public static void main(String[] args) {
        FabrykaAndroidow fabrykaAndroidow = new FabrykaAndroidow();
        Phone xiaomi = fabrykaAndroidow.buyPhone("xiaomi");

    }
}
