package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class PollsAndVotesScreenController extends MainScreenController {

    @FXML
    private TextField cellNumTxt;

    @FXML
    private TextField commentTxt;

    @FXML
    private Hyperlink homeLink;

    @FXML
    private ComboBox<?> moduleComboBox;

    @FXML
    private Hyperlink myCourseLink;

    @FXML
    private Hyperlink noticesLink;

    @FXML
    private Hyperlink pollsVotesLink;

    @FXML
    private TextField rateContentTxt;

    @FXML
    private TextField rateLecturersTxt;

    @FXML
    private ComboBox<?> selectCandidateComboBox;

    @FXML
    private Hyperlink submissionsLink;

    @FXML
    private Button submitEvaluation;

    @FXML
    private Button submitVoteBtn;

    @FXML
    void moduleComboBoxClick(ActionEvent event) {

    }

    @FXML
    void selectCandidateComboBoxClick(ActionEvent event) {

    }
    @FXML
    void submitEvaluationClick(ActionEvent event) {

    }

    @FXML
    void submitVoteClick(ActionEvent event) {

    }

    
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
    void pollsVotesLinkClicked(ActionEvent event) throws IOException {
        super.pollsVotesLinkClicked(event);
    }

    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        super.submissionsLinkClicked(event);
    }

}
