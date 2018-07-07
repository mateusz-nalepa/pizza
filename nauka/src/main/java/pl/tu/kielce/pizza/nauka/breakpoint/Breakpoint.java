package pl.tu.kielce.pizza.nauka.breakpoint;

public class Breakpoint
{
    
    static int globalVariable;
    
    public static void main(String[] args)
    {
        execute(5);
    }
    
    private static void execute(int i)
    {
        globalVariable++;
        
        int methodVariable = 0;
//        methodVariable++; //zmienne method variable muszą być zainicjalizowane pzed użyciem
        
        i+=5;
    
        System.out.println(i);
        
        i-=3;
    
        System.out.println(i);
        }
}
