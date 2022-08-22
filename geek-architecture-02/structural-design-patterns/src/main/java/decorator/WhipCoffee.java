package decorator;

public class WhipCoffee implements Coffee {

    protected SimpleCoffee simpleCoffee;

    public WhipCoffee(SimpleCoffee simpleCoffee) {
        this.simpleCoffee = simpleCoffee;
    }

    @Override
    public int getCost() {
        return simpleCoffee.getCost() + 8;
    }

    @Override
    public String getDescription() {
        return simpleCoffee.getDescription() + " with whip";
    }
}
