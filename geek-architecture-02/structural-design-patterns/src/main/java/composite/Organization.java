package composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    protected List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public float getNetSalaries() {
        float netSalary = 0;
        for (Employee e:
             employees) {
            netSalary += e.getSalary();
        }
        return netSalary;
    }
}
