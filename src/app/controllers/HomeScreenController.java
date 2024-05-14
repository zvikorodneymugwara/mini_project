package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class HomeScreenController extends MainScreenController {

    @FXML
    private Hyperlink personalizeLink;

    @FXML
    private Hyperlink studentInfoLink;

    @FXML
    private Hyperlink submissionsLink;

    @FXML
    private Label welcomeLbl;

    @FXML
    void helpLinkClicked(ActionEvent event) {

    }

    @FXML
    void logoutLinkClicked(ActionEvent event) throws IOException {
        if(user != null){
            user.getHandler().closeAllConnections();
        }
        if(adminUser != null){
            adminUser.getHandler().closeAllConnections();
        }
        switchScene(event, "/screens/login_page.fxml", user);
    }

    @FXML
    void personalizeLinkClicked(ActionEvent event) {
    }

    @FXML
    void studentInfoLinkClicked(ActionEvent event) throws IOException {
        super.myCourseLinkClicked(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing");
    }

}
