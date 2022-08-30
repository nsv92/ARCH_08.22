package decorator;

public class VanillaCoffee implements Coffee {

    protected SimpleCoffee simpleCoffee;

    public VanillaCoffee(SimpleCoffee simpleCoffee) {
        this.simpleCoffee = simpleCoffee;
    }

    @Override
    public int getCost() {
        return simpleCoffee.getCost() + 25;
    }

    @Override
    public String getDescription() {
        return simpleCoffee.getDescription() + " with vanilla";
    }
}
