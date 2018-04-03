package pl.tu.kielce.pizza.nauka.whiz;

class Whiz45
{
    public static void main(String args[])
    {
        
        Long l1 = 10L;
        Long l2 = 9L;
        System.out.print(Long.remainderUnsigned(l1, l2) + Long.divideUnsigned(l1, l2));
    
        System.out.println(Long.remainderUnsigned(7, 4));
        System.out.println(Long.reverse(5));
        System.out.println(Long.divideUnsigned(8, 4));
    }
}
