package app.objects.submissions;

import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.HelperClass;
import app.objects.Admin;
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
                if (company.getCompanyNumber().equals(doc.getReceiver())) {
                    System.out.println(company.toString());
                    System.out.println(doc.toString());
                    company.getDistributedNotes().getTransactions().add(doc);
                }
            }
        }
        returnMessage = new String[2];
    }

    public boolean isCompanyVerified(Company company) {
        return false;
    }

    public boolean addSubmissionDocument(SubmissionDocument document, Admin adminUser) {
        return processDocSubmission(document, adminUser);
    }

    public String[] getReturnMessage() {
        return returnMessage;
    }

    private boolean processDocSubmission(SubmissionDocument document, Admin adminUser) {
        // check if the document exists in the company block
        for (Company company : verifiedCompanies) {
            for (Transaction<SubmissionDocument> companyTransaction : company.getDistributedNotes().getTransactions()) {
                // check if the initial details match
                if (companyTransaction.getSender().equals(document.getStudentNumber())
                        && companyTransaction.getReceiver().equals(document.getRegNumber())) {
                    // the prompt message for the user and the details of the note
                    document.setDocInfo(companyTransaction.getData().getDocInfo());
                    if (document.getSubmissionStatus()) {
                        returnMessage[0] = "Invalid Document";
                        returnMessage[1] = "This document has already been submitted";
                        return false;
                    } else {
                        sendRequest(adminUser);
                        returnMessage[0] = "Successfull Submission";
                        returnMessage[1] = "The document has been submitted successfully and is being proccessed";
                        return true;
                    }
                }
            }
        } // the prompt message for the user and the details of the note
        returnMessage[0] = "Invalid Document";
        returnMessage[1] = "Your Submission Could Not Be Processed. Please Contact Your HOD";
        return false;
    }

    // load objects from txt file for testing
    private void loadSavedCompanies() {
        verifiedCompanies = new ArrayList<>();
        for (Company company : HelperClass.readSavedCompanies()) {
            verifiedCompanies.add(company);
        }
    }

    // load objects from txt file for testing
    private void loadSavedSubmissions() {
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        for (SubmissionDocument doc : HelperClass.readSavedSubmissions()) {
            docSubmissions.getTransactions()
                    .add(new Transaction<SubmissionDocument>(doc.studentNumber, doc.regNumber, doc));
        }
    }

    // TODO networking implementation later
    public void sendRequest(Admin adminUser) {

    }
}
