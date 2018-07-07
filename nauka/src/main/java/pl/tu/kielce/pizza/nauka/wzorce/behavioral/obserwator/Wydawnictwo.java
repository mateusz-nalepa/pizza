package pl.tu.kielce.pizza.nauka.wzorce.behavioral.obserwator;

public class Wydawnictwo {

    public static void main(String[] args) {

        Fakt fakt = new Fakt();
        Grazyna grazyna = new Grazyna(fakt);
        Janusz janusz = new Janusz(fakt);


        fakt.opublikujPost("Post nr 1, wszyscy go widzą!");


        janusz.usunSubskrybcje();


        fakt.opublikujPost("Post nr 2, tylko Grazyna widzi");


    }
}
