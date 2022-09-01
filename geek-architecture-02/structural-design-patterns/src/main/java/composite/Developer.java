package composite;

public class Developer implements Employee {

    protected String name;
    protected float salary;

    protected String[] roles;

    public Developer(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getSalary() {
        return salary;
    }

    @Override
    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String[] getRoles() {
        return roles;
    }
}
