package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz62
{
    public static void main(String args[])
    {
        char[] chars = new char[4];
        chars[0] = 0;
        chars[1] = 2;
        String out = "123456789";
        out.getChars(0, 3, chars, 0);
        for(char c : chars)
        {
            System.out.print(c);
        }
    }
}
