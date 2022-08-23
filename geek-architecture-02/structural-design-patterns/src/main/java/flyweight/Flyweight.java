package flyweight;

public class Flyweight {

    public static void main(String[] args) {
        TeaMaker teaMaker = new TeaMaker();
        TeaShop teaShop = new TeaShop(teaMaker);

        teaShop.takeOrder("aaa", 1);
        teaShop.takeOrder("bbb", 2);
        teaShop.takeOrder("ccc", 3);

        teaShop.serve();
    }
}
