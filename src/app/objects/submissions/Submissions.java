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

public class Submissions {
    private Block<SubmissionDocument> docSubmissions;
    private ArrayList<Company> verifiedCompanies;
    private String[] returnMessage;

    public Submissions() {
        // load saved companies and submissions
        loadSavedCompanies();
        loadSavedSubmissions();
        for (Transaction<SubmissionDocument> doc : docSubmissions.getTransactions()) {
            for (Company company : verifiedCompanies) {
                if (company.getDistributedNotes() == null) {
                    company.initializeDistributedNotes();
                }
                if (company.getCompanyNumber().equals(doc.getSender())) {
                    company.getDistributedNotes().getTransactions().add(doc);
                }
            }
        }
        returnMessage = new String[2];
    }

    public boolean isCompanyVerified(Company company) {
        return false;
    }

    public boolean addSubmissionDocument(SubmissionDocument document) {
        if (document instanceof MedicalSubmission) {
            return processDocSubmission((MedicalSubmission) document);
        }
        return processDocSubmission((Affidavit) document);
    }

    public String[] getReturnMessage() {
        return returnMessage;
    }

    private boolean processDocSubmission(MedicalSubmission document) {
        // check if the document exists in the company block
        for (Company company : verifiedCompanies) {
            for (Transaction<SubmissionDocument> companyTransaction : company.getDistributedNotes().getTransactions()) {
                // check if the initial details match
                if (companyTransaction.getSender().equals(document.getStudentNumber())
                        && companyTransaction.getReceiver().equals(document.getRegNumber())) {
                    // the prompt message for the user and the details of the note
                    document.setDocInfo(companyTransaction.getData().getDocInfo());
                    returnMessage[0] = "";
                    returnMessage[1] = "";
                    return true;
                }
            }
        } // the prompt message for the user and the details of the note
        returnMessage[0] = "";
        returnMessage[1] = "";
        return false;
    }

    private boolean processDocSubmission(Affidavit document) {
        // check if the document exists in the company block
        for (Company company : verifiedCompanies) {
            for (Transaction<SubmissionDocument> companyTransaction : company.getDistributedNotes().getTransactions()) {
                // check if the initial details match
                if (companyTransaction.getSender().equals(document.getStudentNumber())
                        && companyTransaction.getReceiver().equals(document.getRegNumber())) {
                    // the prompt message for the user and the details of the note
                    returnMessage[0] = "";
                    returnMessage[1] = "";
                    return true;

                }
            }
        } // the prompt message for the user and the details of the note
        returnMessage[0] = "";
        returnMessage[1] = "";
        return false;
    }

    // load objects from txt file for testing
    private void loadSavedCompanies() {
        ArrayList<Company> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("data/saved_companies.dat"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    arr.add((Company) object);
                } catch (EOFException e) {
                    break; // Reached end of file
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        verifiedCompanies = new ArrayList<>();
        for (Company company : arr) {
            verifiedCompanies.add(company);
        }
    }

    // load objects from txt file for testing
    private void loadSavedSubmissions() {
        ArrayList<SubmissionDocument> arr = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File("data/saved_submissions.dat"));
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
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        for (SubmissionDocument doc : arr) {
            docSubmissions.getTransactions()
                    .add(new Transaction<SubmissionDocument>(doc.studentNumber, doc.regNumber, doc));
        }
    }
}
