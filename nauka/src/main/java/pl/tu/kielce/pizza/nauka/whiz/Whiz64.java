package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz64
{
    
    public static void main(String args[])
    {
        String[] strs = {"A", "B", "C"};
        String join = String.join("-", strs);
        System.out.println(join.length());
    }
}
