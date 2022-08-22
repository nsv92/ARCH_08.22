package decorator;

public class SimpleCoffee implements Coffee {


    @Override
    public int getCost() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}
