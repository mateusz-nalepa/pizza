package pl.tu.kielce.pizza.nauka.whiz;

class Whiz32
{
    public static void main(String args[])
    {
        new Whiz32().iterator(new int[]{10, 12, 13});
    }
    
    void iterator(int[] i)
    {
        for(int x = 0; x < i.length; System.out.print(i[x] + " "))
        {
            x++;
        }
    }
}

