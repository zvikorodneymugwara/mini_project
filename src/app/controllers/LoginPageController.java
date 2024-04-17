package app.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import app.Secrecy;
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
        File file = new File("data/user_credentials.txt");
        try (Scanner scan = new Scanner(file)) {
            String info = scan.next();
            String[] details = info.split(",");
            if (details[details.length - 2].equals(Secrecy.bytesToHex(Secrecy.getSHA256(passwordTxt.getText())))) {
                studentNumber = studentNumberTxt.getText();
                username = details[1];
                loggedIn = true;
                showMessage(AlertType.CONFIRMATION, "Success", "Login Successfull", "Login Successfull");
                if (Integer.parseInt(details[details.length - 1]) == 0) {
                    switchScene(event, "/screens/home.fxml", new SystemUser(username, studentNumber));
                } else {
                    switchScene(event, "/screens/admin_dashboard.fxml", new SystemUser(username, studentNumber));
                }
            } else {
                showMessage(AlertType.ERROR, "Error", "Login Failed", "Student Number or Password Incorrect");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
