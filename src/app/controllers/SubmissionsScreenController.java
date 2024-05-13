package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import acsse.csc03a3.Transaction;
import app.network.ClientHandler;
import app.objects.Admin;
import app.objects.Vote;
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

/**
 * All user submissions will occur on this screen
 */
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

    // initializes the list of candidates that the user can vote for
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
        if (this.adminUser == null) {
            this.adminUser = new Admin();
        }
    }

    /**
     * Method that submits a document for processing when clicked
     * 
     * @param event
     */
    @FXML
    void submitNoteBtnClick(ActionEvent event) {
        SubmissionDocument doc = null;

        // get the document selection from the user
        if ("Medical Document".equals(docTypeComboBox.getValue())) {
            doc = new MedicalSubmission(docIdTxt.getText(), docDatePicker.getValue().toString(),
                    companyNumTxt.getText(), "", user.getStudentNumber(), false);
        } else {
            doc = new Affidavit(docIdTxt.getText(), docDatePicker.getValue().toString(), companyNumTxt.getText(), "",
                    user.getStudentNumber(), false);
        }

        // send request over the network
        try {
            if (((ClientHandler) user.getHandler()).sendUserRequest(doc)) {
                showMessage(AlertType.INFORMATION, "Status", "Submission Successfull",
                        "Your submission is being processed");
            } else {
                showMessage(AlertType.ERROR, "Error", "Document Already Submitted",
                        "You have Already Submitted this Document!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // clear the selection
        companyNumTxt.setText("");
        docDatePicker.setValue(null);
        docIdTxt.setText("");
        moduleCodeTxt.setText("");
        docTypeComboBox.getSelectionModel().clearSelection();
    }

    /**
     * Method that submits a vote when clicked
     * 
     * @param event
     */
    @FXML
    void submitVoteBtnClick(ActionEvent event) {
        Vote vote = new Vote(candidateComboBox.getValue(), candidate.getCandidateID());
        // vote will be added to the candidate if the user has not voted already
        if (candidate.addVote(new Transaction<Vote>(user.getStudentNumber(), candidate.getCandidateID(), vote))) {
            candidate.setCandidateName(candidateComboBox.getValue());
            showMessage(AlertType.INFORMATION, "Status", "Voted Successfully",
                    "Your vote for " + candidate.getCandidateName() + " was successfully registered");
        } else {
            showMessage(AlertType.ERROR, "Error", "Unable to Vote",
                    "You have already voted for " + candidate.getCandidateName());
        }

        // clear the selection
        candidateComboBox.getSelectionModel().clearSelection();
    }
}
