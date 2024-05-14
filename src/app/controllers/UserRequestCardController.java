package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.HelperClass;
import app.network.AdminResponse;
import app.objects.Company;
import app.objects.submissions.Affidavit;
import app.objects.submissions.SubmissionDocument;
import app.objects.submissions.Submissions;
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

/**
 * User request card for the admin
 */
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

    @FXML
    private Button viewDocInfoBtn;

    /**
     * the request card will have the associated company that sent the documents,
     * the document itself, and some checks to see if the company is registered and
     * if the request has been processed or not
     */
    private Company company;
    private SubmissionDocument reqDocument;
    private boolean companyRegistered = false;

    /**
     * Method to approve the user's request
     * 
     * @param event
     */
    @FXML
    void approveBtnClick(ActionEvent event) {
        String str = "Submission of " + reqDocument.toString() + " Approved"; // notification message
        AdminResponse res = new AdminResponse(true, str);
        updateDocuments(res);
        try {
            // send response to user over network
            adminUser.getHandler().sendResponse(res);
            showMessage(AlertType.INFORMATION, "Request Approved", "Request Approved", str);
            // reload the screen
            switchScene(event, "/screens/admin_submissions.fxml", adminUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to display the information about the document from the reqeust
     * 
     * @param event
     */
    @FXML
    void viewDocInfoBtnClick(ActionEvent event) {
        showMessage(AlertType.INFORMATION, "Document Information", "Document Information", reqDocument.toString());
    }

    @FXML
    void declineBtnClick(ActionEvent event) {
        Stage stage = new Stage();
        TextArea ta = createPopUp(stage);
        Scene popupScene = new Scene(ta.getParent(), 300, 200);
        stage.setScene(popupScene);
        stage.showAndWait();

        String userInput = ta.getText();
        AdminResponse res = new AdminResponse(false, userInput);
        updateDocuments(res);
        try {
            adminUser.getHandler().sendResponse(res);
            switchScene(event, "/screens/admin_submissions.fxml", adminUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the pop up for the admin to specify why the request was denied
     * 
     * @param stage
     * @return
     */
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

    /**
     * Shows the company information to the admin
     * 
     * @param event
     */
    @FXML
    void viewInfoBtnClick(ActionEvent event) {
        // different messages if the company is not on the list of verified companies
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

    /**
     * Method sets the text of the request card when loaded
     */
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

    /**
     * Method to set the company of the request card
     * 
     * @param companies
     */
    public void setCompany(ArrayList<Company> companies) {
        // iterate throught the list of verified companies
        System.out.println(companies);
        for (Company co : companies) {
            /*
             * if the company number matches the request document's company number, set the
             * company to that company
             */
            if (reqDocument.getRegNumber().equals(co.getCompanyNumber())) {
                this.company = co;
                companyRegistered = true;

                String info = "";
                if (submissions == null) {
                    submissions = new Submissions();
                }
                for (Company c : submissions.getVerifiedCompanies()) {
                    SubmissionDocument d = c.getDocument(reqDocument.getDocID());
                    if (d != null) {
                        info = d.getDocInfo();
                    } else if (d == null || info == null || info == "") {
                        info = "No Valid Document Information";
                    }
                }
                reqDocument.setDocInfo(info);
                return;
            }
        }
        /*
         * if the company is not on the list of verified companies, set the company to
         * an unknown company
         */
        company = new Company(reqDocument.getRegNumber(), "Unknown");
    }

    /**
     * sets the document of the requesrt card
     * 
     * @param doc
     */
    public void setRequest(SubmissionDocument doc) {
        reqDocument = doc;
    }

    /**
     * iterate throught the list of documents, and if there is a match, set the
     * document of the request card to that document
     * 
     * @param docs
     */
    private void changeProcessedStatus(ArrayList<SubmissionDocument> docs) {
        for (SubmissionDocument doc : docs) {
            System.out.println(doc.getDocID().equals(reqDocument.getDocID()));
            if (doc.getDocID().equals(reqDocument.getDocID())) {
                doc.setProcessed(true);
                break;
            }
        }
    }

    /**
     * Updates all the documents in the list of requests based onthe response of the
     * admin
     * 
     * @param res the response from the admin
     */
    private void updateDocuments(AdminResponse res) {
        res.setDocId(reqDocument.getDocID());
        ArrayList<AdminResponse> arr = HelperClass.readAdminResponses();
        ArrayList<SubmissionDocument> arr2 = HelperClass.readUserRequests();
        changeProcessedStatus(arr2);

        boolean resFound = false;
        if (arr == null) {
            arr = new ArrayList<>();
            resFound = true;
            arr.add(res);
        } else {
            for (AdminResponse ar : arr) {
                if (ar.getDocId().equals(reqDocument.getDocID())) {
                    ar = res;
                    resFound = true;
                    break;
                }
            }
        }

        boolean docFound = false;
        for (SubmissionDocument doc : arr2) {
            if (doc.getDocID().equals(reqDocument.getDocID())) {
                doc.setProcessed(true);
                docFound = true;
                break;
            }
        }

        reqDocument.setProcessed(true);
        if (!docFound) {
            arr2.add(reqDocument);
        }
        if (!resFound) {
            arr.add(res);
        }
        HelperClass.writeAdminResponses(arr);
        HelperClass.writeUserRequests(arr2);
    }
}
