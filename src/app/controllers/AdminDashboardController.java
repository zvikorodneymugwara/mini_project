package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class AdminDashboardController extends MainScreenController {

    @FXML
    private Hyperlink homeLink;

    @FXML
    private Hyperlink logoutLink;

    @FXML
    private Hyperlink personalizeLink;

    @FXML
    private Hyperlink studentInfoLink;

    @FXML
    private Hyperlink submissionsLink;

    @FXML
    private Label welcomeLbl;

    @FXML
    void logoutLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/login_page.fxml", adminUser);
    }

    @FXML
    void personalizeLinkClicked(ActionEvent event) {

    }

    @FXML
    void studentInfoLinkClicked(ActionEvent event) {

    }

    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/admin_submissions.fxml", user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Admin Dashboard");
    }

    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/admin_dashboard.fxml", user);
    }
}
