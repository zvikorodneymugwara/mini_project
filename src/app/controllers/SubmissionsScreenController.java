package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import acsse.csc03a3.Transaction;
import app.Admin;
import app.Vote;
import app.objects.submissions.Affidavit;
import app.objects.submissions.Candidate;
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
        candidateComboBox.setItems(
                FXCollections.observableArrayList("EFF SRC", "ANC SRC", "Action SA SRC", "ZANU PF SRC", "DA SRC"));
        if (this.submissions == null) {
            this.submissions = new Submissions();
        }
        if (this.candidate == null) {
            this.candidate = new Candidate();
            this.candidate.setCandidateID("" + (new Random().nextInt(1000, 9999)));
        }
        if(this.adminUser == null){
            this.adminUser = new Admin();
        }
    }

    @FXML
    void submitNoteBtnClick(ActionEvent event) {
        SubmissionDocument doc = null;
        if ("Medical Document".equals(docTypeComboBox.getValue())) {
            doc = new MedicalSubmission(docIdTxt.getText(), docDatePicker.getValue().toString(),
                    companyNumTxt.getText(), "", user.getStudentNumber(), false);
        } else {
            doc = new Affidavit(docIdTxt.getText(), docDatePicker.getValue().toString(), companyNumTxt.getText(), "",
                    user.getStudentNumber(), false);
        }
        if (submissions.addSubmissionDocument(doc, adminUser)) {
            user.getSubmissions().getTransactions()
                    .add(new Transaction<SubmissionDocument>(doc.getStudentNumber(), doc.getRegNumber(), doc));
            String[] returnMessages = submissions.getReturnMessage();
            showMessage(AlertType.INFORMATION, "Status", returnMessages[0], returnMessages[1]);
        } else {
            String[] returnMessages = submissions.getReturnMessage();
            showMessage(AlertType.ERROR, "Error", returnMessages[0], returnMessages[1]);
        }
        companyNumTxt.setText("");
        docDatePicker.setValue(null);
        docIdTxt.setText("");
        moduleCodeTxt.setText("");
        docTypeComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    void submitVoteBtnClick(ActionEvent event) {
        Vote vote = new Vote(candidateComboBox.getValue(), candidate.getCandidateID());
        if (candidate.addVote(new Transaction<Vote>(user.getStudentNumber(), candidate.getCandidateID(), vote))) {
            candidate.setCandidateName(candidateComboBox.getValue());
            showMessage(AlertType.INFORMATION, "Status", "Voted Successfully",
                    "Your vote for " + candidate.getCandidateName() + " was successfully registered");
        } else {
            showMessage(AlertType.ERROR, "Error", "Unable to Vote",
                    "You have already voted for " + candidate.getCandidateName());
        }
        candidateComboBox.getSelectionModel().clearSelection();
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
