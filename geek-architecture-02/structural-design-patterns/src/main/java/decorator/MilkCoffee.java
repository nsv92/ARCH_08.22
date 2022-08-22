package decorator;

public class MilkCoffee implements Coffee {

    protected SimpleCoffee simpleCoffee;

    public MilkCoffee(SimpleCoffee simpleCoffee) {
        this.simpleCoffee = simpleCoffee;
    }


    @Override
    public int getCost() {
        return simpleCoffee.getCost() + 3;
    }

    @Override
    public String getDescription() {
        return simpleCoffee.getDescription() + " with milk";
    }
}
