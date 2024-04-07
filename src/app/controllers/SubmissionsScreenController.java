package app.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class SubmissionsScreenController extends MainScreenController {

    @FXML
    private ComboBox<?> candidateComboBox;

    @FXML
    private TextField companyNumTxt;

    @FXML
    private DatePicker docDatePicker;

    @FXML
    private TextField docIdTxt;

    @FXML
    private ComboBox<?> docTypeComboBox;

    @FXML
    private Hyperlink homeLink;

    @FXML
    private TextField moduleCodeTxt;

    @FXML
    private Hyperlink myCourseLink;

    @FXML
    private Hyperlink noticesLink;

    @FXML
    private TextField phoneNumTxt;

    @FXML
    private Hyperlink submissionsLink;

    @FXML
    private Button submitNoteBtn;

    @FXML
    private Button submitVoteBtn;

    @FXML
    void candidateComboBoxClick(ActionEvent event) {

    }

    @FXML
    void docDatePickerClick(ActionEvent event) {

    }

    @FXML
    void docTypeComboBoxClick(ActionEvent event) {

    }

    @FXML
    void submitNoteBtnClick(ActionEvent event) {

    }

    @FXML
    void submitVoteBtnClick(ActionEvent event) {

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
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        super.submissionsLinkClicked(event);
    }

}
