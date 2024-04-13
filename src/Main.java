import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.objects.Company;
import app.objects.SystemUser;
import app.objects.submissions.Affidavit;
import app.objects.submissions.MedicalSubmission;
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

    public static void writeCompnaines() {
        Company saps = new Company("500", "South African Police Services");
        Company helenJoseph = new Company("501", "Helen Joseph Hospital");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("data/saved_companies.dat"));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {

            // Write the Company objects to the ObjectOutputStream
            objectOutputStream.writeObject(saps);
            objectOutputStream.writeObject(helenJoseph);

            System.out.println("Companies successfully written to file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSubmissions() {
        Calendar calendar = Calendar.getInstance();

        // Set the calendar to the current date and time
        calendar.setTime(new Date());

        // Subtract two months from the current date
        calendar.add(Calendar.MONTH, -2);
        Affidavit affidavit = new Affidavit("1", new Date().toString(), "100", "Student Was at  a Funeral",
                "221100999");
        MedicalSubmission sickNote = new MedicalSubmission("2", calendar.getTime().toString(), "101",
                "Student Had Eye Surgery", "221100999");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("data/saved_submissions.dat"));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {

            // Write the Company objects to the ObjectOutputStream
            objectOutputStream.writeObject(affidavit);
            objectOutputStream.writeObject(sickNote);

            System.out.println("Submissions successfully written to file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSceneName(String filePath) {
        if (new File("data/saved_submissions.dat").exists() == false) {
            writeSubmissions();
        }

        if (new File("data/saved_companies.dat").exists() == false) {
            writeCompnaines();
        }

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