import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String path = "screens/login_page.fxml";
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            stage.setTitle(getSceneName(path));

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getSceneName(String filePath) {
        Pattern pattern = Pattern.compile("[^/]+(?=\\.fxml$)");
        Matcher matcher = pattern.matcher(filePath);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String fileName = matcher.group();
            fileName = fileName.replaceAll("_", " ");
            fileName = fileName.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
            result.append(fileName.substring(0, 1).toUpperCase()).append(fileName.substring(1)).append(" ");
        }
        return capitalize(result.toString());
    }

    public static String capitalize(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
        }
        return result.toString().trim();
    }
}