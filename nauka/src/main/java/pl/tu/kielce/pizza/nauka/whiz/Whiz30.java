package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz30
{
    public static void main(String[] args)
    {
        String in = "abc";
//        String in = "AAA";
        
        switch(in)
        {
            case "AAA":
                System.out.println(2);
                //leci po kolei, dopóki nie napotka BREAK, jak jest "AAA"
            case "ABC":
                System.out.println(1);
            case "BBB":
                System.out.println(3);
                break;
            case "":
                System.out.println(4);
        }
    }
}
