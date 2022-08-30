package flyweight;

import java.util.ArrayList;
import java.util.HashMap;

public class TeaShop {

    protected TeaMaker teaMaker;

    protected ArrayList<Order> orders = new ArrayList<>();

    public TeaShop(TeaMaker teaMaker) {
        this.teaMaker = teaMaker;
    }

    public void takeOrder(String teaType, int table) {
        orders.add(new Order(teaType, table));
        teaMaker.makeTea(teaType);
    }
    public void serve() {
        for (Order order:
             orders) {
            System.out.println("Serving tea to table #" + order.table);
        }
    }
}
