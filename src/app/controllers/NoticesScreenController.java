package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.HelperClass;
import app.network.AdminResponse;
import app.objects.submissions.SubmissionDocument;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class NoticesScreenController extends MainScreenController {
    @FXML
    private VBox noticesPane;

    // notices screen has a list of submission notices and requests
    private ArrayList<AdminResponse> notices;
    private ArrayList<SubmissionDocument> requests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing");
    }

    /**
     * Method initializes the notices screen with any notices. Notices can either be
     * a response from the admin or a notification of a submission pending
     * 
     * @throws IOException
     */
    public void initializeNotices() throws IOException {
        // load the responses and requests
        notices = HelperClass.readAdminResponses();
        requests = HelperClass.readUserRequests();

        // create the notices items
        if (notices != null) {
            for (AdminResponse response : notices) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/notice_item.fxml"));
                Parent root = loader.load();
                MainScreenController controller = loader.getController();
                ((NoticeItemController) controller).setResponse(response);
                ((NoticeItemController) controller).init();
                noticesPane.getChildren().add(root);
            }
        }

        // create the request items
        if (requests != null) {
            for (SubmissionDocument reqDocument : requests) {
                if(!reqDocument.getProcessed()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/notice_item.fxml"));
                    Parent root = loader.load();
                    MainScreenController controller = loader.getController();
                    ((NoticeItemController) controller).setRequest(reqDocument);
                    ((NoticeItemController) controller).init();
                    noticesPane.getChildren().add(root);
                }
            }
        }
    }
}
