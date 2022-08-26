package proxy;

public class LabDoor implements Door {

    @Override
    public void open() {
        System.out.println("Opening the door");
    }

    @Override
    public void close() {
        System.out.println("Closing the door");
    }
}
