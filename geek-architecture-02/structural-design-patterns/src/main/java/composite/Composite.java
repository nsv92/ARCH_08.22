package composite;

public class Composite {

    public static void main(String[] args) {

        Developer aaa = new Developer("AAA BBB", 555);
        Designer ccc = new Designer("CCC DDD", 222);

        Organization company = new Organization();
        company.addEmployee(aaa);
        company.addEmployee(ccc);
        System.out.println(company.getNetSalaries());
    }
}
