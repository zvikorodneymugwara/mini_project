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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import app.network.AdminResponse;
import app.objects.Company;
import app.objects.submissions.Affidavit;
import app.objects.submissions.MedicalSubmission;
import app.objects.submissions.SubmissionDocument;

/**
 * Helper class has a bunch of methods that are used to make the program run
 * properly. These methods don't belong to any particular class, but are used
 * all aound the application.
 */
public class HelperClass {
    // initializes binary and text files if they don't exist
    public static void init() {
        if (new File("data/saved_submissions.dat").exists() == false) {
            writeSubmissions();
        }

        if (new File("data/saved_companies.dat").exists() == false) {
            writeCompnaines();
        }

        if (new File("data/user_credentials.txt").exists() == false) {
            try {
                insertUser("Bernard", bytesToHex(getSHA256("hashed_password")), 221100999, 0);
                insertUser("Benjamin", bytesToHex(getSHA256("hashed_password")), 201100101, 1);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    // writes companies to the binary files
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

    // writes submissions to binary files
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

    // writes users to file
    public static void insertUser(String username, String hashedPassword, int studentNumber, int userType) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data/user_credentials.txt", true))) {
            String userData = username + "," + studentNumber + "," + hashedPassword + "," + userType;
            System.out.println(userData);
            pw.println(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads companies from binary file
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

    // load submissions from binary file
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads the saved user requests
     * 
     * @return all user requests
     */
    public static ArrayList<SubmissionDocument> readUserRequests() {
        ArrayList<SubmissionDocument> arr = null;
        File file = new File("data/submissions/saved_submissions.dat");
        if (file.exists()) {
            arr = new ArrayList<>();
            try (FileInputStream fileInputStream = new FileInputStream(file);
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
                    } catch (EOFException | ClassNotFoundException e) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return arr;
    }

    /**
     * writes admin responses to file
     * 
     * @param responses
     */
    public static void writeAdminResponses(ArrayList<AdminResponse> responses) {
        // Check if the file exists before creating streams
        File file = new File("data/submissions/saved_requests.dat");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads admin responses from file
     * 
     * @return the read responses from the file
     */
    public static ArrayList<AdminResponse> readAdminResponses() {
        ArrayList<AdminResponse> arr = null;
        File file = new File("data/submissions/saved_requests.dat");
        if (file.exists()) {
            arr = new ArrayList<>();
            try (FileInputStream fileInputStream = new FileInputStream(file);
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
        }
        return arr;
    }

    // hashes the string for the password
    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes());
    }

    // converts the hashed bytes into a string
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
