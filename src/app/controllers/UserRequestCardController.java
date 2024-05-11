package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.network.AdminResponse;
import app.objects.Company;
import app.objects.submissions.Affidavit;
import app.objects.submissions.SubmissionDocument;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;

//TODO add view details button to the document
public class UserRequestCardController extends MainScreenController {

    @FXML
    private Button approveBtn;

    @FXML
    private Label companyNameLbl;

    @FXML
    private Button declineBtn;

    @FXML
    private Label docIDLbl;

    @FXML
    private Label docTypeLbl;

    @FXML
    private Label studentNumberLbl;

    @FXML
    private Label student_NameLbl;

    @FXML
    private Button viewInfoBtn;
    Company company;
    SubmissionDocument reqDocument;
    boolean companyRegistered = false;
    boolean processed = false;

    @FXML
    void approveBtnClick(ActionEvent event) {
        String str = "Submission of " + reqDocument.toString() + " Approved";
        AdminResponse res = new AdminResponse(true, str);
        try {
            adminUser.getHandler().sendResponse(res);
            showMessage(AlertType.INFORMATION, "Request Approved", "Request Approved", str);
            processed = true;
            changeProcessedStatus(adminUser.getHandler().getUserRequests());
            switchScene(event, "/screens/admin_submissions.fxml", adminUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void declineBtnClick(ActionEvent event) {
        Stage stage = new Stage();
        TextArea ta = createPopUp(stage);
        Scene popupScene = new Scene(ta.getParent(), 300, 200);
        stage.setScene(popupScene);
        stage.showAndWait();

        String userInput = ta.getText();
        System.out.println("User input: " + userInput);
        AdminResponse res = new AdminResponse(false, userInput);

        try {
            adminUser.getHandler().sendResponse(res);
            processed = true;
            changeProcessedStatus(adminUser.getHandler().getUserRequests());
            switchScene(event, "/screens/admin_submissions.fxml", adminUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TextArea createPopUp(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Enter Details");

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);

        Button closeButton = new Button("Sumbit");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(textArea, closeButton);
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        return textArea;
    }

    @FXML
    void viewInfoBtnClick(ActionEvent event) {
        if (!companyRegistered) {
            showMessage(AlertType.ERROR, "Invalid Company Information", "Comany Information",
                    "Company Data Is Not Registered: \n\n" + company.toString());
        } else {
            showMessage(AlertType.INFORMATION, "Company Information", "Comany Information", company.toString());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing Request Cards");
    }

    public void init() {
        if (reqDocument != null) {
            companyNameLbl.setText(company.getCompanyName());
            docIDLbl.setText(reqDocument.getDocID());
            String s = "";
            if (reqDocument instanceof Affidavit) {
                s = "Affidavit";
            } else {
                s = "Medical Document";
            }
            docTypeLbl.setText(s);
            studentNumberLbl.setText(reqDocument.getStudentNumber());
            student_NameLbl.setText("Bernard");
        }
    }

    public void setCompany(ArrayList<Company> companies) {
        for (Company co : companies) {
            System.out.println("Document Company Number " + reqDocument.getRegNumber());
            System.out.println("Company Number " + co.getCompanyNumber());
            System.out.println(reqDocument.getRegNumber().equals(co.getCompanyNumber()));
            if (reqDocument.getRegNumber().equals(co.getCompanyNumber())) {
                this.company = co;
                companyRegistered = true;
                break;
            }
        }
    }

    public void setRequest(SubmissionDocument doc) {
        reqDocument = doc;
    }

    private void changeProcessedStatus(ArrayList<SubmissionDocument> docs) {
        for (SubmissionDocument doc : docs) {
            if (doc.getDocID().equals(reqDocument.getDocID())) {
                doc.setProcessed(this.processed);
                break;
            }
        }
    }
}
