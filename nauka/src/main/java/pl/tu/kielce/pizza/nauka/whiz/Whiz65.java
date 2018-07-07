package pl.tu.kielce.pizza.nauka.whiz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Whiz65
{
    
    public static void main(String args[])
    {
        // m -> minuta
        // M -> miesiac
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y/m/d");
        LocalDateTime ldt = LocalDateTime.of(2015, 10, 10, 11, 22);
        System.out.println(dtf.format(ldt));
    }
}
