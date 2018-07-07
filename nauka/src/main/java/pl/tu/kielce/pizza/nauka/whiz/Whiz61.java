package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz61 {

    public static void main(String args[]) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F'};
        String out = String.copyValueOf(chars, 1, 4);
        System.out.println(out);
    }
}
  