package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NoticesScreenController  extends MainScreenController{
    
    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        super.homeLinkClicked(event);
    }

    @FXML
    void myCourseLinkClicked(ActionEvent event) throws IOException {
        super.myCourseLinkClicked(event);

    }

    @FXML
    void noticesLinkClicked(ActionEvent event) throws IOException {
        super.noticesLinkClicked(event);
    }


    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        super.submissionsLinkClicked(event);
    }
}
