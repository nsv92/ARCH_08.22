package decorator;

public class Decorator {

    public static void main(String[] args) {
        SimpleCoffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
        System.out.println("-------------------");

        MilkCoffee milk = new MilkCoffee(coffee);
        System.out.println(milk.getDescription());
        System.out.println(milk.getCost());
        System.out.println("-------------------");

        WhipCoffee whip = new WhipCoffee(coffee);
        System.out.println(whip.getDescription());
        System.out.println(whip.getCost());
        System.out.println("-------------------");

        VanillaCoffee vanilla = new VanillaCoffee(coffee);
        System.out.println(vanilla.getDescription());
        System.out.println(vanilla.getCost());
    }
}
