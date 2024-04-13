package app.controllers;

import java.io.IOException;

import acsse.csc03a3.Transaction;
import app.objects.submissions.Affidavit;
import app.objects.submissions.MedicalSubmission;
import app.objects.submissions.SubmissionDocument;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
        SubmissionDocument doc = null;
        if (docTypeComboBox.getSelectionModel().getSelectedItem() instanceof MedicalSubmission) {
            doc = new MedicalSubmission(username, title, studentNumber, studentNumber, studentNumber);
        } else {
            doc = new Affidavit(username, title, studentNumber, studentNumber, studentNumber);
        }
        if (submissions.addSubmissionDocument(doc)) {
            user.getSubmissions().getTransactions()
                    .add(new Transaction<SubmissionDocument>(doc.getStudentNumber(), doc.getRegNumber(), doc));
            showMessage(AlertType.INFORMATION, "Status", "Successful Submission",
                    "Submission was Successfully Processed");
        } else {
            showMessage(AlertType.ERROR, "Error", "Unsuccessful Submission",
                    "Your Submission Could Not Be Processed. Please Contact Your HOD");
        }
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
