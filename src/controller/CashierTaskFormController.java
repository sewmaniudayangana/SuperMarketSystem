package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class CashierTaskFormController {
    public Label menuOne;
    public Label menuTwo;
    public Label txtTime;
    public Label txtDate;
    public AnchorPane adminContext;
    public FontAwesomeIconView cart;

    public void initialize(){
        menuOne.setOnMouseClicked(event -> {
            //panel1.setVisible(true);
            URL resource = getClass().getResource("../view/LoginForm.fxml");
            Parent load = null;
            try {
                load = FXMLLoader.load(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage window = (Stage) adminContext.getScene().getWindow();
            window.setScene(new Scene(load));
        });
        menuTwo.setOnMouseClicked(event -> {
            URL resource = getClass().getResource("../view/SettingForm.fxml");
            Parent load = null;
            try {
                load = FXMLLoader.load(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene=new Scene(load);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        });

        loadDateAndTime();
        animation(cart);
    }

    public void animation(FontAwesomeIconView imageView){
        ScaleTransition scaleTransition=new ScaleTransition();
        scaleTransition.setNode(imageView);
        scaleTransition.setDuration(Duration.millis(500));
        scaleTransition.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.setByX(2.0);
        scaleTransition.setByY(2.0);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();

            String state = null;
            if (currentTime.getHour() >= 12) {
                state = "PM";
            } else {
                state = "AM";
            }
            txtTime.setText("" + String.format("%02d",currentTime.getHour()) + ":" + String.format("%02d", currentTime.getMinute()) + ":" + String.format("%02d", currentTime.getSecond())+" "+state);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void goToModifyOrderPage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ModifyOrderDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToManageOrderPage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageOrderPage.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToAddNewCustomerPage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewCustomer.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void goToMakePaymentPage(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MakePaymentPage.fxml");
        Parent load = FXMLLoader.load(resource);
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }
}
