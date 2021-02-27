package org.example.model;

public class Employee {
    private int id;
    private double salary;
    private String type;

    public Employee() {
    }

    public Employee(int id, double salary, String type) {
        this.id = id;
        this.salary = salary;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
