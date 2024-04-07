package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public abstract class MainScreenController extends SceneController {

    @FXML
    protected Hyperlink noticesLink;

    @FXML
    protected Hyperlink helpLink;

    @FXML
    protected Hyperlink homeLink;

    @FXML
    protected Hyperlink logoutLink;

    @FXML
    protected Hyperlink myCourseLink;

    @FXML
    void noticesLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/notices.fxml");

    }

    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/home.fxml");

    }

    @FXML
    void myCourseLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/my_course.fxml");

    }

    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/submissions.fxml");

    }

}
