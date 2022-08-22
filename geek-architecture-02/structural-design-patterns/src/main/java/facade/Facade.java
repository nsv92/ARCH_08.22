package facade;

public class Facade {

    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn();
        System.out.println("---------");
        computer.turnOff();
    }
}
