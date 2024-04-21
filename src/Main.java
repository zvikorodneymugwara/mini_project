import java.io.IOException;
import java.net.Socket;

import app.HelperClass;
import app.network.ClientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static String hostname = "localhost";
    private static int portNumber = 8080;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HelperClass.init();
        ClientHandler handler = new ClientHandler(new Socket(hostname, portNumber));
        String path = "screens/login_page.fxml";
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            stage.setTitle(HelperClass.getSceneName(path));

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}