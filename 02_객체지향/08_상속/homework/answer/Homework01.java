/** Employee 와 Manager 상속. */
public class Homework01 {
    public static void main(String[] args) {
        Employee e = new Employee("홍길동", 3_000_000);
        Manager m = new Manager("김부장", 5_000_000, 1_000_000);
        e.report();
        m.report();
    }
}

class Employee {
    protected String name;
    protected long salary;

    Employee(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    void report() {
        System.out.println(name + " 월급 " + salary);
    }
}

class Manager extends Employee {
    private long bonus;

    Manager(String name, long salary, long bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    @Override
    void report() {
        System.out.println(name + " 월급 " + salary + " + 보너스 " + bonus
                + " = " + (salary + bonus));
    }
}
