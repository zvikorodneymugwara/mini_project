package app.objects.submissions;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.objects.Company;

public class Submission {
    Block<SubmissionDocument> docSubmissions;
    ArrayList<Block<Company>> verifiedCompanies;

    public Submission() {
        // load saved companies and submissions
        loadSavedCompanies();
        loadSavedSubmissions();
    }

    public boolean isCompanyVerified(Company company) {
        return false;
    }

    public boolean addSubmissionDocument(SubmissionDocument document) {
        if (document instanceof MedicalSubmission && processDocSubmission((MedicalSubmission) document)) {
            return true;
        } else if (processDocSubmission((Affidavit) document)) {
            return true;
        }

        return false;
    }

    private boolean processDocSubmission(MedicalSubmission document) {
        // check if the document exists in the company block
        for (Block<Company> companyBlock : verifiedCompanies) {
            for (Transaction<Company> companyTransaction : companyBlock.getTransactions()) {
                // check if the initial details match
                if (companyTransaction.getSender().equals(document.getStudentNumber())
                        && companyTransaction.getReceiver().equals(document.getRegNumber())) {

                    return true;

                }
            }
        }
        return false;
    }

    private boolean processDocSubmission(Affidavit document) {
        // check if the document exists in the company block
        for (Block<Company> companyBlock : verifiedCompanies) {
            for (Transaction<Company> companyTransaction : companyBlock.getTransactions()) {
                // check if the initial details match
                if (companyTransaction.getSender().equals(document.getStudentNumber())
                        && companyTransaction.getReceiver().equals(document.getRegNumber())) {

                    return true;

                }
            }
        }
        return false;
    }

    // load objects from txt file for testing
    private void loadSavedCompanies() {
        ArrayList<Block<Company>> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("saved_transactions.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    @SuppressWarnings("unchecked")
                    Block<Company> blk = (Block<Company>) object;
                    if (blk != null) {
                        arr.add(blk);
                    }
                } catch (EOFException e) {
                    break; // Reached end of file
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Block<Company> blk : arr) {
            verifiedCompanies.add(blk);
        }
    }

    // load objects from txt file for testing
    private void loadSavedSubmissions() {
        ArrayList<SubmissionDocument> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("saved_transactions.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    arr.add((SubmissionDocument) object);
                } catch (EOFException e) {
                    break; // Reached end of file
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (SubmissionDocument doc : arr) {
            docSubmissions.getTransactions()
                    .add(new Transaction<SubmissionDocument>(doc.studentNumber, doc.regNumber, doc));
        }
    }
}
