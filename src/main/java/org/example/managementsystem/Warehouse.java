package org.example.managementsystem;

public class Warehouse {
    private int id;
    private String name, location;
    private int capacity;
    private String createdAt, manager, phone;

    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Warehouse(int id, String name, String location, int capacity, String createdAt, String manager, String phone) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.createdAt = createdAt;
        this.manager = manager;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String getCreatedAt() { return createdAt; }
    public String getManager() { return manager; }
    public String getPhone() { return phone; }
}