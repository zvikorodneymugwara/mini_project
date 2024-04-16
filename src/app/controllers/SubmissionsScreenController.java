package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import acsse.csc03a3.Transaction;
import app.objects.submissions.Affidavit;
import app.objects.submissions.MedicalSubmission;
import app.objects.submissions.SubmissionDocument;
import app.objects.submissions.Submissions;
import javafx.collections.FXCollections;
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
    private ComboBox<String> candidateComboBox;

    @FXML
    private TextField companyNumTxt;

    @FXML
    private DatePicker docDatePicker;

    @FXML
    private TextField docIdTxt;

    @FXML
    private ComboBox<String> docTypeComboBox;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        docTypeComboBox.setItems(FXCollections.observableArrayList("Medical Document", "Affidavit"));
        if (this.submissions == null) {
            this.submissions = new Submissions();
        }
    }

    @FXML
    void submitNoteBtnClick(ActionEvent event) {
        SubmissionDocument doc = null;
        if ("Medical Document".equals(docTypeComboBox.getValue())) {
            doc = new MedicalSubmission(docIdTxt.getText(), docDatePicker.getValue().toString(),
                    companyNumTxt.getText(), "", user.getStudentNumber());
        } else {
            doc = new Affidavit(docIdTxt.getText(), docDatePicker.getValue().toString(), companyNumTxt.getText(), "",
                    user.getStudentNumber());
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
