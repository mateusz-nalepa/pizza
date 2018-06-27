package pl.tu.kielce.pizza.nauka.whiz.diagnostic;

public class Diag_Whiz_14 {

    public static void main(String[] args) {
        System.out.println(true ^ false | true);
        System.out.println("Main");
        new Diag_Whiz_14();

        for (int x =10, y = 6; x > y;   System.out.println(x--));
    }

    //to taki @PostConstruct dla obiektów
    {
        System.out.println("Whiz");
    }

    static {
        System.out.println("Static");
    }
}
