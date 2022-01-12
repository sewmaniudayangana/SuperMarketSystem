package controller;

import db.DbConnection;
import javafx.scene.control.Alert;
import javafx.scene.input.InputMethodTextRun;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController implements CustomerService{

    private String id;

    @Override
    public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getTitle());
        stm.setObject(3,c.getName());
        stm.setObject(4,c.getAddress());
        stm.setObject(5,c.getCity());
        stm.setObject(6,c.getProvince());
        stm.setObject(7,c.getPostalCode());
        return stm.executeUpdate()>0;

    }

    @Override
    public boolean deleteCustomer(String c) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Customer WHERE CustId=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );

        } else {
            return null;
        }
    }
}
