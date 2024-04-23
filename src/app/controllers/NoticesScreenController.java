package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.network.AdminResponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class NoticesScreenController extends MainScreenController {
    @FXML
    private VBox noticesPane;

    ArrayList<AdminResponse> notices;

    // TODO networking implementation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing");
    }

    public void initializeNotices() throws IOException {
        notices = user.getHandler().getAdminResponses();
        if (notices != null) {
            for (AdminResponse response : this.notices) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/notice_item.fxml"));
                Parent root = loader.load();
                MainScreenController controller = loader.getController();
                ((NoticeItemController) controller).setResponse(response);
                ((NoticeItemController) controller).init();
                noticesPane.getChildren().add(root);
            }
        }
    }
}
