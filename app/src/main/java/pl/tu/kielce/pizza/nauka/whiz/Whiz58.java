package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz58
{
    
    public static void main(String[] args)
    {
        StringBuilder sb = new StringBuilder("Whiz");
        System.out.println(sb.length() + ":" + sb.capacity());
        
        sb.append("Labs");
        System.out.println(sb.length() + ":" + sb.capacity());
    
        sb.append("1234123412341234123412341234123412");
        System.out.println(sb.length() + ":" + sb.capacity());
        
        System.out.println(sb.length() + sb.capacity());
    }
    
}
