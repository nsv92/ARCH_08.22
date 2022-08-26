package adapter;

public class Hunt {


    public static void main(String[] args) {

        WildDogAdapter wildDogAdapter = new WildDogAdapter(new WildDog());
        Hunter hunter = new Hunter();
        hunter.hunt(wildDogAdapter);
    }
}
