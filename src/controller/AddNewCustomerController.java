package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class AddNewCustomerController {
    public TextField txtId;
    public TextField txtTitle;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtPostalCode;

    public void customerAddOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Customer c1 = new Customer(
                txtId.getText(),txtTitle.getText(),txtName.getText(),txtAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()
        );
        if(new CustomerController().addCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();txtPostalCode.clear();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            txtId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();txtPostalCode.clear();

        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if (new CustomerController().deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchCustomer(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String customerId = txtId.getText();

        Customer c1= new CustomerController().getCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }
    void setData(Customer c){
        txtId.setText(c.getId());
        txtTitle.setText(c.getTitle());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostalCode.setText(c.getPostalCode());

    }

    public void veiwCuromerDetailOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ViewCustomerTable.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
