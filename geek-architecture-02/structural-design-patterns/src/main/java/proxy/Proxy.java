package proxy;

public class Proxy {

    public static void main(String[] args) {

        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("aaa");
        door.open("pass");
        door.close();
    }
}
