package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.HelperClass;
import app.objects.submissions.SubmissionDocument;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

/**
 * submission screen for the admin
 */
public class AdminSubmissionsScreenController extends HomeScreenController {

    @FXML
    private Hyperlink homeLink;

    @FXML
    private Hyperlink personalizeLink2;

    @FXML
    private Hyperlink submissionsLink;

    @FXML
    private VBox userRequestsPane;

    private ArrayList<SubmissionDocument> requests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    /**
     * Inititializes all user requests for admin to view and process
     * 
     * @throws IOException
     */
    public void initilizeUserRequestsScreen() throws IOException {
        requests = HelperClass.readUserRequests();
        if (requests != null) { // if there are requests
            for (SubmissionDocument doc : requests) {
                if (doc.getProcessed() == false) { // if the document has not been proccessed
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/user_request_card.fxml"));
                    Parent root = loader.load();
                    MainScreenController controller = loader.getController();
                    ((UserRequestCardController) controller).setRequest(doc);
                    ((UserRequestCardController) controller).adminUser = adminUser;
                    ((UserRequestCardController) controller).setCompany(adminUser.getVerifiedCompanies());
                    ((UserRequestCardController) controller).init();
                    // display the request
                    userRequestsPane.getChildren().add(root);
                }
            }
        }
    }

    @FXML
    void personalizeLinkClicked(ActionEvent event) {
    }

    @FXML
    void submissionsLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/admin_submissions.fxml", user);
    }

    @FXML
    void homeLinkClicked(ActionEvent event) throws IOException {
        switchScene(event, "/screens/admin_dashboard.fxml", user);
    }
}
