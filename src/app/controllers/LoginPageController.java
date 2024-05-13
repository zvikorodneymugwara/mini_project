package app.controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import app.HelperClass;
import app.objects.Admin;
import app.objects.SystemUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class LoginPageController extends SceneController {

    @FXML
    private Button loginBtn;

    @FXML
    private VBox main_vbox;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField studentNumberTxt;

    @FXML
    void loginBtnClicked(ActionEvent event) {
        boolean found = false; // checks if user has been found in the below file
        File file = new File("data/user_credentials.txt");

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNext()) {
                String info = scan.next();
                String[] details = info.split(",");
                // if the details match
                if (details[details.length - 2].equals(HelperClass.bytesToHex(HelperClass.getSHA256(passwordTxt.getText())))
                        && studentNumberTxt.getText().equals(details[details.length - 3])) {
                    studentNumber = studentNumberTxt.getText();
                    username = details[1];
                    loggedIn = found = true; // the user has been found and logged in
                    // show pop up
                    showMessage(AlertType.CONFIRMATION, "Success", "Login Successfull", "Login Successfull");

                    // user type determines the UI that will be loaded
                    if (Integer.parseInt(details[details.length - 1]) == 0) {
                        switchScene(event, "/screens/home.fxml", new SystemUser(username, studentNumber, 0));
                        break;
                    } else {
                        switchScene(event, "/screens/admin_dashboard.fxml", new Admin(username, studentNumber));
                        break;
                    }
                }
            }
            if (!found) { // wrong credentials
                showMessage(AlertType.ERROR, "Error", "Login Failed", "Student Number or Password Incorrect");
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

}
