package app.controllers;

import java.io.IOException;

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

    protected void switchScene(ActionEvent event, String scenePath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(scenePath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
