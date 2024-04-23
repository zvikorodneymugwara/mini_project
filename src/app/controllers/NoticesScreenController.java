package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.network.AdminResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class NoticesScreenController extends MainScreenController {
    @FXML
    private VBox noticesPane;

    ArrayList<AdminResponse> notices;

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

    // TODO networking implementation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing");
        try {
            initializeNotices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeNotices() throws IOException {
        // notices = user.getHandler().getAdminResponses();
        notices = new ArrayList<>();
        notices.add(new AdminResponse(true, "Test Response 1"));
        notices.add(new AdminResponse(true, "Test Response 2"));
        notices.add(new AdminResponse(true, "Test Response 3"));
        int counter = 0;
        for (AdminResponse response : this.notices) {
            if (counter > this.notices.size() - 1) {
                break;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/notice_item.fxml"));
            Parent root = loader.load();
            MainScreenController controller = loader.getController();
            ((NoticeItemController) controller).setResponse(response);
            ((NoticeItemController) controller).init();
            noticesPane.getChildren().add(root);
            counter++;
        }
    }
}
