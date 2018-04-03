package pl.tu.kielce.pizza.nauka.whiz;

public class Whiz38
{
}

class Animal {
    public void eat() throws Exception { System.out.print("Animal eats");}
}

class Dog extends Animal {
    public void eat() { System.out.print("Dog eats"); }
    
    public static void main(String [] args) {
        Animal a = new Dog();
        Dog d = new Dog();
        d.eat();
//        a.eat(); //rzuca błąd kompilacji, bo 'a' jest typu Animal i nie ma nigdzie przechwytywania wyjątku
    }
}
