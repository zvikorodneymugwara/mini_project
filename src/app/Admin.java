package app;

import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.objects.Company;
import app.objects.submissions.SubmissionDocument;

public class Admin {
    private ArrayList<UserRequest> requests;
    private Block<SubmissionDocument> docSubmissions;
    private ArrayList<Company> verifiedCompanies;

    private String name;
    private String adminNumber;

    public Admin(String name, String adminNumber) {
        this.name = name;
        this.adminNumber = adminNumber;
        this.requests = new ArrayList<>();
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        verifiedCompanies = new ArrayList<>();
        insertTransactions();
    }

    public Admin() {
        this.requests = new ArrayList<>();
        verifiedCompanies = new ArrayList<>();
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        insertTransactions();
    }

    public void approveRequest(UserRequest request) {

    }

    public void declineRequest(UserRequest request) {

    }

    public ArrayList<UserRequest> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<UserRequest> requests) {
        this.requests = requests;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public Block<SubmissionDocument> getDocSubmissions() {
        return docSubmissions;
    }

    public ArrayList<Company> getVerifiedCompanies() {
        return verifiedCompanies;
    }

    private void insertTransactions() {
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

    // TODO Networking implementation
    public ArrayList<String> recieveRequest() {
        return null;
    }

    // TODO Networking implementation
    public void sendOutcome() {

    }
}
