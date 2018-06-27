package pl.tu.kielce.pizza.nauka.whiz.diagnostic;

import java.util.Arrays;

public class Diag_Whiz_1 {

    public static void main(String[] args) {

        String[][] strings = {{"A", "Z"}, {"C", "D", "S"}, {"L"}};

        //Class Cast Exception, bo nie ma `sort` który przyjmuje tablicę tablic
        // kompiluje się dlatego, że wykonuje się metoda z parametrem Object[]
        Arrays.sort(strings);

        for (String[] string : strings) {
            for (String s : string) {
                System.out.println(s);
            }
        }
    }
}
