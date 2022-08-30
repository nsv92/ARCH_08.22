package flyweight;

public class Order {

    protected String teaType;

    protected int table;

    public Order(String teaType, int table) {
        this.teaType = teaType;
        this.table = table;
    }
}
