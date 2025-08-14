package org.example.managementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Controller {

    @FXML
    private Button empAddButton;

    @FXML
    private Button empClearButton;

    @FXML
    private Button empDeleteButton;

    @FXML
    private TableColumn<?, ?> empDobColumn;

    @FXML
    private DatePicker empDobPicker;

    @FXML
    private TableColumn<?, ?> empEmailColumn;

    @FXML
    private TextField empEmailField;

    @FXML
    private TableColumn<?, ?> empFirstNameColumn;

    @FXML
    private TextField empFirstNameField;

    @FXML
    private TableColumn<?, ?> empHireDateColumn;

    @FXML
    private DatePicker empHireDatePicker;

    @FXML
    private TableColumn<?, ?> empIdColumn;

    @FXML
    private TextField empIdField;

    @FXML
    private TableColumn<?, ?> empLastNameColumn;

    @FXML
    private TextField empLastNameField;

    @FXML
    private Button empListButton;

    @FXML
    private TableColumn<?, ?> empPhoneColumn;

    @FXML
    private TextField empPhoneField;

    @FXML
    private TableColumn<?, ?> empPositionColumn;

    @FXML
    private TextField empPositionField;

    @FXML
    private TableColumn<?, ?> empSalaryColumn;

    @FXML
    private TextField empSalaryField;

    @FXML
    private Button empUpdateButton;

    @FXML
    private TableColumn<?, ?> empWarehouseIdColumn;

    @FXML
    private TextField empWarehouseIdField;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private Button prodAddButton;

    @FXML
    private TableColumn<?, ?> prodAddedDateColumn;

    @FXML
    private DatePicker prodAddedDatePicker;

    @FXML
    private TableColumn<?, ?> prodCategoryColumn;

    @FXML
    private TextField prodCategoryField;

    @FXML
    private Button prodClearButton;

    @FXML
    private Button prodDeleteButton;

    @FXML
    private TableColumn<?, ?> prodIdColumn;

    @FXML
    private TextField prodIdField;

    @FXML
    private Button prodListButton;

    @FXML
    private TableColumn<?, ?> prodNameColumn;

    @FXML
    private TextField prodNameField;

    @FXML
    private TableColumn<?, ?> prodPriceColumn;

    @FXML
    private TextField prodPriceField;

    @FXML
    private TableColumn<?, ?> prodQuantityColumn;

    @FXML
    private TextField prodQuantityField;

    @FXML
    private TableColumn<?, ?> prodSupplierColumn;

    @FXML
    private TextField prodSupplierField;

    @FXML
    private Button prodUpdateButton;

    @FXML
    private TableColumn<?, ?> prodWarehouseIdColumn;

    @FXML
    private TextField prodWarehouseIdField;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableView<Warehouse> warehouseTableView;

    @FXML
    private Button whAddButton;

    @FXML
    private TableColumn<?, ?> whCapacityColumn;

    @FXML
    private TextField whCapacityField;

    @FXML
    private Button whClearButton;

    @FXML
    private TableColumn<?, ?> whCreatedAtColumn;

    @FXML
    private DatePicker whCreatedAtPicker;

    @FXML
    private Button whDeleteButton;

    @FXML
    private TableColumn<?, ?> whIdColumn;

    @FXML
    private TextField whIdField;

    @FXML
    private Button whListButton;

    @FXML
    private TableColumn<?, ?> whLocationColumn;

    @FXML
    private TextField whLocationField;

    @FXML
    private TableColumn<?, ?> whManagerColumn;

    @FXML
    private TextField whManagerField;

    @FXML
    private TableColumn<?, ?> whNameColumn;

    @FXML
    private TextField whNameField;

    @FXML
    private TableColumn<?, ?> whPhoneColumn;

    @FXML
    private TextField whPhoneField;

    @FXML
    private Button whUpdateButton;



    @FXML
    void handleAddEmployee(ActionEvent event) {

        try {
            String firstName = empFirstNameField.getText();
            String lastName = empLastNameField.getText();
            String email = empEmailField.getText();
            String phone = empPhoneField.getText();

            // Safe date handling
            String dateOfBirth = null;
            if (empDobPicker.getValue() != null) {
                dateOfBirth = empDobPicker.getValue().toString();
            } else {
                throw new Exception("Doğum tarihi zorunludur");
            }

            // Validate salary
            Double salary;
            try {
                salary = Double.parseDouble(empSalaryField.getText());
            } catch (NumberFormatException e) {
                throw new Exception("Maaş geçerli bir sayı olmalıdır");
            }

            // Safe date handling
            String hireDate = null;
            if (empHireDatePicker.getValue() != null) {
                hireDate = empHireDatePicker.getValue().toString();
            } else {
                throw new Exception("Doğum tarihi gereklidir");
            }

            String position = empPositionField.getText();

            // Validate warehouse ID
            int warehouseId;
            try {
                warehouseId = Integer.parseInt(empWarehouseIdField.getText());
            } catch (NumberFormatException e) {
                throw new Exception("Depo kimliği geçerli bir sayı olmalıdır");
            }

            DatabaseHelper.addEmployee(firstName, lastName, email, phone, dateOfBirth,
                    salary, hireDate, position, warehouseId);

            handleClearEmployeeForm(event);

            // Refresh the employee list
            handleListEmployeeForm(event);
        } catch (Exception e) {
            System.err.println("Çalışan eklenirken hata oluştu: " + e.getMessage());
            // Show error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Çalışan eklenemedi");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void handleAddProduct(ActionEvent event) {
        try {
            String name = prodNameField.getText();
            String category = prodCategoryField.getText();
            double price = Double.parseDouble(prodPriceField.getText());
            int quantity = Integer.parseInt(prodQuantityField.getText());
            String supplier = prodSupplierField.getText();
            String addedDate = prodAddedDatePicker.getValue().toString();
            int warehouseId = Integer.parseInt(prodWarehouseIdField.getText());

            DatabaseHelper.addProduct(name, category, price, quantity, supplier,
                addedDate, warehouseId);

            handleClearProductForm(event); // Form alanlarını temizle
        } catch (Exception e) {
            System.err.println("Ürün eklenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleAddWarehouse(ActionEvent event) {
        try {
            String name = whNameField.getText();
            int capacity = Integer.parseInt(whCapacityField.getText());
            String createdAt = whCreatedAtPicker.getValue().toString();
            String location = whLocationField.getText();
            String manager = whManagerField.getText();
            String phone = whPhoneField.getText();

            DatabaseHelper.addWarehouse(name, capacity, createdAt, location,
                manager, phone);

            handleClearWarehouseForm(event); // Form alanlarını temizle
        } catch (Exception e) {
            System.err.println("Depo eklenirken hata oluştu: " + e.getMessage());
        }
    }


    // Add the following methods to the Controller class:
    @FXML
    void handleClearEmployeeForm(ActionEvent event) {
        empFirstNameField.clear();
        empLastNameField.clear();
        empEmailField.clear();
        empPhoneField.clear();
        empDobPicker.getEditor().clear();
        empSalaryField.clear();
        empHireDatePicker.getEditor().clear();
        empPositionField.clear();
        empWarehouseIdField.clear();
    }

    @FXML
    void handleClearProductForm(ActionEvent event) {
        prodNameField.clear();
        prodCategoryField.clear();
        prodPriceField.clear();
        prodQuantityField.clear();
        prodSupplierField.clear();
        prodAddedDatePicker.getEditor().clear();
        prodWarehouseIdField.clear();
    }

    @FXML
    void handleClearWarehouseForm(ActionEvent event) {
        whNameField.clear();
        whCapacityField.clear();
        whCreatedAtPicker.getEditor().clear();
        whLocationField.clear();
        whManagerField.clear();
        whPhoneField.clear();
    }


    // Add the following methods to the Controller class:
    @FXML
    void handleDeleteEmployee(ActionEvent event) {
        try {
            String idText = empIdField.getText();
            if (idText != null && !idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                DatabaseHelper.deleteEmployee(id);
                handleClearEmployeeForm(event);
            } else {
                System.err.println("Hata: Lütfen silmek için bir çalışan seçin");
            }
        } catch (Exception e) {
            System.err.println("Çalışan silinirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {
        try {
            String idText = prodIdField.getText();
            if (idText != null && !idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                DatabaseHelper.deleteProduct(id);
                handleClearProductForm(event);
            } else {
                System.err.println("Hata: Lütfen silmek için bir ürün seçin");
            }
        } catch (Exception e) {
            System.err.println("Ürün silinirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleDeleteWarehouse(ActionEvent event) {
        try {
            String idText = whIdField.getText();
            if (idText != null && !idText.isEmpty()) {
                int id = Integer.parseInt(idText);
                DatabaseHelper.deleteWarehouse(id);
                handleClearWarehouseForm(event);
            } else {
                System.err.println("Hata: Lütfen silmek için bir depo seçin");
            }
        } catch (Exception e) {
            System.err.println("Depo silinirken hata oluştu: " + e.getMessage());
        }
    }


    // Add the following methods to the Controller class:
    @FXML
    void handleListEmployeeForm(ActionEvent event) {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String query = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ObservableList<Employee> employeeList = FXCollections.observableArrayList();

            while (rs.next()) {
                employeeList.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("date_of_birth"),
                    rs.getDouble("salary"),
                    rs.getString("hire_date"),
                    rs.getString("position"),
                    rs.getInt("warehouse_id")
                ));
            }

            employeeTableView.getItems().clear();
            employeeTableView.setItems(employeeList);

            DatabaseHelper.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Çalışanlar listelenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleListProductForm(ActionEvent event) {
        try {
            Connection conn = DatabaseHelper.getConnection();
            String query = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ObservableList<Product> productList = FXCollections.observableArrayList();

            while (rs.next()) {
                productList.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("supplier"),
                    rs.getString("added_date"),
                    rs.getInt("warehouse_id")
                ));
            }

            productTableView.getItems().clear();
            productTableView.setItems(productList);

            DatabaseHelper.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Ürünler listelenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleListWarehouseForm(ActionEvent event) {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String query = "SELECT * FROM warehouse";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ObservableList<Warehouse> warehouseList = FXCollections.observableArrayList();

            while (rs.next()) {
                warehouseList.add(new Warehouse(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("capacity"),
                        rs.getString("created_at"),
                        rs.getString("manager"),
                        rs.getString("phone")
                ));
            }

            warehouseTableView.getItems().clear();
            warehouseTableView.setItems(warehouseList);
        } catch (SQLException e) {
            System.err.println("Depolar listelenirken hata oluştu: " + e.getMessage());
        } finally {
            if (conn != null) {
                DatabaseHelper.closeConnection(conn);
            }
        }
    }


    // Add the following methods to the Controller class:
    @FXML
    void handleUpdateEmployee(ActionEvent event) {
        try {
            int id = Integer.parseInt(empIdField.getText());
            String firstName = empFirstNameField.getText();
            String lastName = empLastNameField.getText();
            String email = empEmailField.getText();
            String phone = empPhoneField.getText();
            String dateOfBirth = empDobPicker.getValue().toString();
            Double salary = Double.parseDouble(empSalaryField.getText());
            String hireDate = empHireDatePicker.getValue().toString();
            String position = empPositionField.getText();
            int warehouseId = Integer.parseInt(empWarehouseIdField.getText());

            DatabaseHelper.updateEmployee(id, firstName, lastName, email, phone,
                dateOfBirth, salary, hireDate, position, warehouseId);

            handleClearEmployeeForm(event);
        } catch (Exception e) {
            System.err.println("Çalışan güncellenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdateProduct(ActionEvent event) {
        try {
            int id = Integer.parseInt(prodIdField.getText());
            String name = prodNameField.getText();
            String category = prodCategoryField.getText();
            double price = Double.parseDouble(prodPriceField.getText());
            int quantity = Integer.parseInt(prodQuantityField.getText());
            String supplier = prodSupplierField.getText();
            String addedDate = prodAddedDatePicker.getValue().toString();
            int warehouseId = Integer.parseInt(prodWarehouseIdField.getText());

            DatabaseHelper.updateProduct(id, name, category, price, quantity,
                supplier, addedDate, warehouseId);

            handleClearProductForm(event);
        } catch (Exception e) {
            System.err.println("Ürün güncellenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdateWarehouse(ActionEvent event) {
        try {
            int id = Integer.parseInt(whIdField.getText());
            String name = whNameField.getText();
            int capacity = Integer.parseInt(whCapacityField.getText());
            String createdAt = whCreatedAtPicker.getValue().toString();
            String location = whLocationField.getText();
            String manager = whManagerField.getText();
            String phone = whPhoneField.getText();

            DatabaseHelper.updateWarehouse(id, name, capacity, createdAt,
                location, manager, phone);

            handleClearWarehouseForm(event);
        } catch (Exception e) {
            System.err.println("Depo güncellenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        // Employee TableView columns
        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        empFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        empLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        empEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        empPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        empDobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        empSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        empHireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        empPositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        empWarehouseIdColumn.setCellValueFactory(new PropertyValueFactory<>("warehouseId"));

        // Product TableView columns
        prodIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        prodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        prodSupplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        prodAddedDateColumn.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        prodWarehouseIdColumn.setCellValueFactory(new PropertyValueFactory<>("warehouseId"));

        // Warehouse TableView columns
        whIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        whNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        whLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        whCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        whCreatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        whManagerColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        whPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        employeeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                empIdField.setText(String.valueOf(newSelection.getId()));
                empFirstNameField.setText(newSelection.getFirstName());
                empLastNameField.setText(newSelection.getLastName());
                empEmailField.setText(newSelection.getEmail());
                empPhoneField.setText(newSelection.getPhone());
                // Handle date picker - requires conversion from string to LocalDate
                if (newSelection.getDateOfBirth() != null) {
                    try {
                        empDobPicker.setValue(LocalDate.parse(newSelection.getDateOfBirth()));
                    } catch (Exception e) {
                        System.err.println("Doğum tarihi ayrıştırılırken hata oluştu: " + e.getMessage());
                    }
                }
                empSalaryField.setText(String.valueOf(newSelection.getSalary()));
                if (newSelection.getHireDate() != null) {
                    try {
                        empHireDatePicker.setValue(LocalDate.parse(newSelection.getHireDate()));
                    } catch (Exception e) {
                        System.err.println("İşe alım tarihi ayrıştırılırken hata oluştu: " + e.getMessage());
                    }
                }
                empPositionField.setText(newSelection.getPosition());
                empWarehouseIdField.setText(String.valueOf(newSelection.getWarehouseId()));
            }
        });


        productTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                prodIdField.setText(String.valueOf(newSelection.getId()));
                prodNameField.setText(newSelection.getName());
                prodCategoryField.setText(newSelection.getCategory());
                prodPriceField.setText(String.valueOf(newSelection.getPrice()));
                prodQuantityField.setText(String.valueOf(newSelection.getQuantity()));
                prodSupplierField.setText(newSelection.getSupplier());
                if (newSelection.getAddedDate() != null) {
                    try {
                        prodAddedDatePicker.setValue(LocalDate.parse(newSelection.getAddedDate()));
                    } catch (Exception e) {
                        System.err.println("Eklenen tarihin ayrıştırılmasında hata oluştu: " + e.getMessage());
                    }
                }
                prodWarehouseIdField.setText(String.valueOf(newSelection.getWarehouseId()));
            }
        });

        // Warehouse TableView selection listener
        warehouseTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                whIdField.setText(String.valueOf(newSelection.getId()));
                whNameField.setText(newSelection.getName());
                whLocationField.setText(newSelection.getLocation());
                whCapacityField.setText(String.valueOf(newSelection.getCapacity()));
                if (newSelection.getCreatedAt() != null) {
                    try {
                        whCreatedAtPicker.setValue(LocalDate.parse(newSelection.getCreatedAt()));
                    } catch (Exception e) {
                        System.err.println("Oluşturulma tarihi ayrıştırılırken hata oluştu: " + e.getMessage());
                    }
                }
                whManagerField.setText(newSelection.getManager());
                whPhoneField.setText(newSelection.getPhone());
            }
        });
    }

}
