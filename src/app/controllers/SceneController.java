package app.controllers;

import java.io.IOException;

import app.network.AdminHandler;
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

/**
 * Ancestor class for every screen displayed in the application.
 */
public abstract class SceneController {

    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    protected String title;
    protected boolean loggedIn;

    // user instances and data
    protected SystemUser user;
    protected Admin adminUser;
    protected String username;
    protected String studentNumber;

    /**
     * Method switches scenes when a link is clicked
     * 
     * @param event
     * @param scenePath the path of the specified scene
     * @param user      the user instance that is running will be passed from scene
     *                  to scene
     * @throws IOException
     */
    protected void switchScene(ActionEvent event, String scenePath, SystemUser user) throws IOException {
        Parent root = loadUser(user, scenePath);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to load the user and return the root node for the scene
     * 
     * @param user
     * @param screenPath
     * @return the node for the screen that will be loaded
     * @throws IOException
     */
    public Parent loadUser(SystemUser user, String screenPath) throws IOException {
        // get the controller of the root node
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screenPath));
        Parent rootNode = loader.load();
        SceneController controller = loader.getController();

        // set the user for the controller
        if (user instanceof Admin) {
            if (controller.adminUser == null) {
                controller.adminUser = (Admin) user;
            } else {
                AdminHandler handler = controller.adminUser.getHandler();
                controller.adminUser = null;
                user.setHandler(handler);
                controller.adminUser = (Admin) user;
            }
            if (controller.user == null) {
                controller.user = new SystemUser();
            }
        }
        controller.user = user;

        // some screens need to be initialized before being displayed as they have
        // dynamic data on them
        if (controller instanceof MyCourseScreenController) {
            ((MyCourseScreenController) controller).initializeScreen();
        } else if (controller instanceof NoticesScreenController) {
            ((NoticesScreenController) controller).initializeNotices();
        } else if (controller instanceof AdminSubmissionsScreenController) {
            ((AdminSubmissionsScreenController) controller).initilizeUserRequestsScreen();
        }
        return rootNode;
    }

    /**
     * Every scene has the ability to display a pop up
     * 
     * @param alertType
     * @param title
     * @param headerText
     * @param contentText
     */
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
}
