package org.example.managementsystem;

import java.sql.*;
import java.util.Scanner;


public class DatabaseHelper {
    static Scanner scanner = new Scanner(System.in);
    static String URL = "jdbc:mysql://localhost:3306/management_system";
    static final String USER = "root"; // MySQL Kullanıcı Adı
    static final String PASSWORD = "12345"; // MySQL Şifresi

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not connect to database!");
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not close the connection!");
        }
    }

    public static void addEmployee(String first_name, String last_name, String email, String phone, String date_of_birth, Double salary, String hire_date, String position, int warehouse_id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();
            String query = "INSERT INTO employees (first_name, last_name, email, phone, date_of_birth, salary, hire_date, position, warehouse_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, date_of_birth);
            preparedStatement.setDouble(6, salary);
            preparedStatement.setString(7, hire_date);
            preparedStatement.setString(8, position);
            preparedStatement.setInt(9, warehouse_id);
            preparedStatement.executeUpdate();
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not add the employee! " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateEmployee(int id, String first_name, String last_name, String email, String phone, String date_of_birth, Double salary, String hire_date, String position, int warehouse_id) {
        try {
            String query = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone = ?, date_of_birth = ?, salary = ?, hire_date = ?, position = ?, warehouse_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, date_of_birth);
            preparedStatement.setDouble(6, salary);
            preparedStatement.setString(7, hire_date);
            preparedStatement.setString(8, position);
            preparedStatement.setInt(9, warehouse_id);
            preparedStatement.setInt(10, id);
            preparedStatement.executeUpdate();
            System.out.println("Employee updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not update the employee!");
        }
    }

    public static void deleteEmployee(int id) {
        try {
            String query = "DELETE FROM employees WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Employee deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not delete the employee!");
        }
    }


    public static void addProduct(String name, String category, double price, int quantity, String supplier, String added_date, int warehouse_id) {
        try {
            String query = "INSERT INTO products (name, category, price, quantity, supplier, added_date , warehouse_id ) VALUES (?, ?, ?, ?, ?, ? , ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setString(5, supplier);
            preparedStatement.setString(6, added_date);
            preparedStatement.setInt(7, warehouse_id);
            preparedStatement.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not add the product!");
        }
    }

    public static void updateProduct(int id, String name, String category, double price, int quantity, String supplier, String added_date, int warehouse_id) {
        try {
            String query = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ?, supplier = ?, added_date = ?, warehouse_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setString(5, supplier);
            preparedStatement.setString(6, added_date);
            preparedStatement.setInt(7, warehouse_id);
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not update the product!");
        }
    }

    public static void deleteProduct(int id) {
        try {
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not delete the product!");
        }
    }


    public static void addWarehouse(String name, int capacity, String created_at, String location, String manager, String phone) {
        try {
            String query = "INSERT INTO warehouse (name, capacity , created_at , location, manager , phone) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, capacity);
            preparedStatement.setString(3, created_at);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, manager);
            preparedStatement.setString(6, phone);

            preparedStatement.executeUpdate();
            System.out.println("Warehouse added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not add the warehouse!");
        }
    }

    public static void updateWarehouse(int id, String name, int capacity, String created_at, String location, String manager, String phone) {
        try {
            String query = "UPDATE warehouse SET name = ?, capacity = ?, created_at = ?, location = ?, manager = ?, phone = ? WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, capacity);
            preparedStatement.setString(3, created_at);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, manager);
            preparedStatement.setString(6, phone);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            System.out.println("Warehouse updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not update the warehouse!");
        }
    }

    public static void deleteWarehouse(int id) {
        try {
            String query = "DELETE FROM warehouse WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Warehouse deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Could not delete the warehouse!");
        }
    }


}




