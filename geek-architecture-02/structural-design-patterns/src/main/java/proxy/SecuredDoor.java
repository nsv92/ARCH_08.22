package proxy;

public class SecuredDoor {

    protected Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    public void open(String password) {
        if (authenticate(password)) {
            door.open();
        } else {
            System.out.println("NO");
        }
    }

    public boolean authenticate(String password) {
        return password == "pass";
    }

    public void close() {
        door.close();
    }
}
