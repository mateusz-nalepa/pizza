package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz19 {
    
    public static void main(String[] args) {
        
        String[ ] sts = {"A","B","C"};
        
        for (String i : sts) {
            continue;
//            System.out.print(i); //unreachable statement
        }
    }
}
