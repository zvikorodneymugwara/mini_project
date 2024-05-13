import java.io.IOException;

import app.HelperClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // initialize variables
        HelperClass.init();
        Image icon = new Image(getClass().getResourceAsStream("img/uj.png"));
        String path = "screens/login_page.fxml";
        try {
            // load login page
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            stage.setTitle("Student Portal");
            stage.getIcons().add(icon);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}