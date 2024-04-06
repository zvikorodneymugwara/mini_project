import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import app.Secrecy;

public class UserCredentialsTxt {

    public static void main(String[] args) {
        // Example usage to insert user credentials
        try {
            insertUser("Bernard", Secrecy.bytesToHex(Secrecy.getSHA256("hashed_password")), 221100999);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(String username, String hashedPassword, int studentNumber) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data/user_credentials.txt"))) {
            System.out.println(username + "," + studentNumber + "," + hashedPassword);
            pw.println(username + "," + studentNumber + "," + hashedPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
