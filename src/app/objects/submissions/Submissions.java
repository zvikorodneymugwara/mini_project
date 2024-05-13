package app.objects.submissions;

import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.HelperClass;
import app.objects.Admin;
import app.objects.Company;

/**
 * a submissions objects tracks all the submissions and verified companies to
 * compare to and verify submissions against
 */
public class Submissions {
    private Block<SubmissionDocument> docSubmissions;
    private ArrayList<Company> verifiedCompanies;

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
    }

    // getters and setters

    public boolean isCompanyVerified(Company company) {
        return false;
    }

    public boolean addSubmissionDocument(SubmissionDocument document, Admin adminUser) {
        return processDocSubmission(document, adminUser);
    }

    public ArrayList<Company> getVerifiedCompanies() {
        return verifiedCompanies;
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
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // load objects from dat file for testing
    private void loadSavedCompanies() {
        verifiedCompanies = new ArrayList<>();
        for (Company company : HelperClass.readSavedCompanies()) {
            verifiedCompanies.add(company);
        }
    }

    // load objects from dat file for testing
    private void loadSavedSubmissions() {
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        for (SubmissionDocument doc : HelperClass.readSavedSubmissions()) {
            docSubmissions.getTransactions()
                    .add(new Transaction<SubmissionDocument>(doc.studentNumber, doc.regNumber, doc));
        }
    }
}
