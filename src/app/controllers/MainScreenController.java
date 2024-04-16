package app.controllers;

import java.io.IOException;

import app.objects.SystemUser;
import app.objects.submissions.Submissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

public abstract class MainScreenController extends SceneController implements Initializable {

    protected Submissions submissions;

    protected SystemUser user;

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
        switchScene(event, "/screens/notices.fxml", user);
    }

    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/home.fxml", user);
    }

    @FXML
    void myCourseLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/my_course.fxml", user);
    }

    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/submissions.fxml", user);
    }
}
