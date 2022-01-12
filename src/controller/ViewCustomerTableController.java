package controller;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.CustomerTM;

import java.sql.*;

public class ViewCustomerTableController {
    public AnchorPane context;
    public TableView <CustomerTM>tblCustomer;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPCode;


    public void initialize(){
            try {

                colId.setCellValueFactory(new PropertyValueFactory<>("id"));
                colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
                colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
                colPCode.setCellValueFactory(new PropertyValueFactory<>("pCode"));

                loadAllCustomers();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        private void loadAllCustomers() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/SuperMarket",
                    "root", "1234"
            );

            PreparedStatement stm = con.prepareStatement("SELECT * FROM Customer");
            ResultSet rst = stm.executeQuery();

            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            while (rst.next()) {
                obList.add(new CustomerTM(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7)
                ));
            }
            tblCustomer.setItems(obList);
        }
    }
