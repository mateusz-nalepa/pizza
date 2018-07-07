package pl.tu.kielce.pizza.nauka.whiz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Whiz68
{
    
    public static void main(String[] args)
    {
        
        LocalDateTime l = LocalDateTime.of(LocalDate.of(2015, 3, 3), LocalTime.of(11, 22));
        l = l.withDayOfMonth(12);
        System.out.println(l.getMonth() + " : " + l.getDayOfMonth() + " : " + l.getHour());
    }
}
