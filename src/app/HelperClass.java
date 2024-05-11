package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import app.network.AdminResponse;
import app.objects.Company;
import app.objects.submissions.Affidavit;
import app.objects.submissions.MedicalSubmission;
import app.objects.submissions.SubmissionDocument;

public class HelperClass {
    public static void init() {
        if (new File("data/saved_submissions.dat").exists() == false) {
            writeSubmissions();
        }

        if (new File("data/saved_companies.dat").exists() == false) {
            writeCompnaines();
        }

        if (new File("data/user_credentials.txt").exists() == false) {
            try {
                insertUser("Bernard", Secrecy.bytesToHex(Secrecy.getSHA256("hashed_password")), 221100999, 0);
                insertUser("Benjamin", Secrecy.bytesToHex(Secrecy.getSHA256("hashed_password")), 201100101, 1);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
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

    public static void writeCompnaines() {
        Company saps = new Company("100", "South African Police Services");
        Company helenJoseph = new Company("101", "Helen Joseph Hospital");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("data/saved_companies.dat"));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {

            objectOutputStream.writeObject(saps);
            objectOutputStream.writeObject(helenJoseph);

            System.out.println("Companies successfully written to file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSubmissions() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -2);

        Affidavit affidavit = new Affidavit("1", new Date().toString(), "100", "Student Was at  a Funeral",
                "221100999", true);
        MedicalSubmission sickNote = new MedicalSubmission("2", calendar.getTime().toString(), "101",
                "Student Had Eye Surgery", "221100999", false);

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("data/saved_submissions.dat"));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {

            objectOutputStream.writeObject(affidavit);
            objectOutputStream.writeObject(sickNote);

            System.out.println("Submissions successfully written to file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(String username, String hashedPassword, int studentNumber, int userType) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data/user_credentials.txt", true))) {
            String userData = username + "," + studentNumber + "," + hashedPassword + "," + userType;
            System.out.println(userData);
            pw.println(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Company> readSavedCompanies() {
        ArrayList<Company> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("data/saved_companies.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    arr.add((Company) object);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arr;
    }

    // load objects from txt file for testing
    public static ArrayList<SubmissionDocument> readSavedSubmissions() {
        ArrayList<SubmissionDocument> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("data/saved_submissions.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    if (object instanceof Affidavit) {
                        arr.add((Affidavit) object);
                    } else if (object instanceof MedicalSubmission) {
                        arr.add((MedicalSubmission) object);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static void writeUserRequests(ArrayList<SubmissionDocument> docs) {
        File file = new File("data/submissions/saved_submissions.dat");
        // Check if the file exists before creating streams
        if (!file.exists()) {
            // Create the file if it doesn't exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
            for (SubmissionDocument doc : docs) {
                objectOutputStream.writeObject(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SubmissionDocument> readUserRequests() {
        ArrayList<SubmissionDocument> arr = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(new File("data/submissions/saved_submissions.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    if (object instanceof Affidavit) {
                        arr.add((Affidavit) object);
                    } else if (object instanceof MedicalSubmission) {
                        arr.add((MedicalSubmission) object);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return arr;

    }

    public static void writeAdminResponses(ArrayList<AdminResponse> responses) {
        File file = new File("data/submissions/saved_respones.dat");
        // Check if the file exists before creating streams
        if (!file.exists()) {
            // Create the file if it doesn't exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
            for (AdminResponse res : responses) {
                objectOutputStream.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<AdminResponse> readAdminResponses() {
        ArrayList<AdminResponse> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("data/submissions/saved_respones.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    arr.add((AdminResponse) object);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arr;
    }
}
