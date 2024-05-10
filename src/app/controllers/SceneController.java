package app.controllers;

import java.io.IOException;

import app.objects.Admin;
import app.objects.SystemUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public abstract class SceneController {

    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    protected String title;
    protected String username;
    protected String studentNumber;
    protected boolean loggedIn;

    protected void switchScene(ActionEvent event, String scenePath, SystemUser user) throws IOException {
        Parent root = loadUser(user, scenePath);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Parent loadUser(SystemUser user, String screenPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screenPath));
        Parent rootNode = loader.load();
        MainScreenController controller = loader.getController();
        if (user instanceof Admin) {
            controller.adminUser = (Admin) user;
            if(controller.user == null){
                controller.user = new SystemUser();
            }
        }
            controller.user = user;
        if (controller instanceof MyCourseScreenController) {
            ((MyCourseScreenController) controller).initializeScreen();
        } else if (controller instanceof NoticesScreenController) {
            ((NoticesScreenController) controller).initializeNotices();
        } else if (controller instanceof AdminSubmissionsScreenController) {
            ((AdminSubmissionsScreenController) controller).initilizeUserRequestsScreen();
        }
        return rootNode;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    protected void showMessage(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        // Handling user response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                alert.close();
            }
        });
    }

    public String getTitle() {
        return title;
    }
}
