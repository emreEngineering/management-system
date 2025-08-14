package org.example.managementsystem;

public class Employee {
    private int id;
    private String firstName, lastName, email, phone, dateOfBirth, hireDate, position;
    private double salary;
    private int warehouseId;

    public Employee(int id, String firstName, String lastName, String email, String phone, String dateOfBirth, double salary, String hireDate, String position, int warehouseId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.hireDate = hireDate;
        this.position = position;
        this.warehouseId = warehouseId;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDateOfBirth() { return dateOfBirth; }
    public double getSalary() { return salary; }
    public String getHireDate() { return hireDate; }
    public String getPosition() { return position; }
    public int getWarehouseId() { return warehouseId; }
}