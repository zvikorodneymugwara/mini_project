package app.controllers;

import java.io.IOException;

import app.objects.submissions.Candidate;
import app.objects.submissions.Submissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

/**
 * Controls the main screen of the application. Extends SceneController,
 * implements Initializable and is abstract
 */
public abstract class MainScreenController extends SceneController implements Initializable {

    // local instance variables for submissions and for voting
    protected Submissions submissions;
    protected Candidate candidate;

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

    /**
     * Switches to notices screen
     * @param event
     * @throws IOException
     */
    @FXML
    void noticesLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/notices.fxml", user);
    }

    /**
     * Switches to home screen
     * @param event
     * @throws IOException
     */
    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/home.fxml", user);
    }

    /**
     * Switches to the course screen
     * @param event
     * @throws IOException
     */
    @FXML
    void myCourseLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/my_course.fxml", user);
    }

    /**
     * Switches to the submissions screen
     * @param event
     * @throws IOException
     */
    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/submissions.fxml", user);
    }

    /**
     * Gets the candidate for voting
     * @return
     */
    public Candidate getCandidate() {
        return candidate;
    }

    /**
     * Sets the candidate for voting
     * @param candidate
     */
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
