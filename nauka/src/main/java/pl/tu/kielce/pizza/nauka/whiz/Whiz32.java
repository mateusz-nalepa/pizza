package pl.tu.kielce.pizza.nauka.whiz;

class Whiz32
{
    public static void main(String args[])
    {
        new Whiz32().iterator(new int[]{10, 12, 13});
    }
    
    void iterator(int[] i)
    {
        for(int x = 0; x < i.length; asd(i, x))
        {
            x++;
        }
    }

    private void asd(int[] i, int x) {
        System.out.print(i[x] + " ");
        //12 ,13,, ArrayIndexOutOfBoundsException
    }
}

