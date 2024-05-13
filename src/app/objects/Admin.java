package app.objects;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.HelperClass;
import app.network.AdminHandler;
import app.network.AdminResponse;
import app.objects.submissions.SubmissionDocument;

public class Admin extends SystemUser {
    private Block<SubmissionDocument> docSubmissions;
    private ArrayList<Company> verifiedCompanies;
    private AdminHandler handler;

    private String name;
    private String adminNumber;

    // constructors

    public Admin(String name, String adminNumber) {
        super(name, adminNumber, 1);
        this.name = name;
        this.adminNumber = adminNumber;
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        verifiedCompanies = HelperClass.readSavedCompanies();
        insertTransactions();
        try {
            handler = new AdminHandler(new Socket("localhost", 2021));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Admin() {
        super();
        verifiedCompanies = HelperClass.readSavedCompanies();
        docSubmissions = new Block<SubmissionDocument>("", new ArrayList<>());
        insertTransactions();
    }

    // getters and setters

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

    public AdminHandler getHandler() {
        return handler;
    }

    /**
     * after vetting, the document is added to the block of the company in the
     * verified companies list
     */
    private void insertTransactions() {
        for (Transaction<SubmissionDocument> doc : docSubmissions.getTransactions()) {
            for (Company company : verifiedCompanies) {
                if (company.getDistributedNotes() == null) {
                    company.initializeDistributedNotes();
                }
                if (company.getCompanyNumber().equals(doc.getReceiver())) {
                    company.getDistributedNotes().getTransactions().add(doc);
                }
            }
        }
    }

    /**
     * sends the outcome of the processing of the request
     * 
     * @param status
     * @param response
     */
    public void sendOutcome(boolean status, String response) {
        AdminResponse res = new AdminResponse(status, response);
        try {
            handler.sendResponse(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
