package org.example.managementsystem;

public class Product {
    private int id;
    private String name, category, supplier, addedDate;
    private double price;
    private int quantity, warehouseId;

    public Product(int id, String name, String category, double price, int quantity, String supplier, String addedDate, int warehouseId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
        this.addedDate = addedDate;
        this.warehouseId = warehouseId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getSupplier() { return supplier; }
    public String getAddedDate() { return addedDate; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getWarehouseId() { return warehouseId; }
}
