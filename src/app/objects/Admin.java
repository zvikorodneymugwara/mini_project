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

//TODO Sort out admin functionality
public class Admin extends SystemUser {
    private Block<SubmissionDocument> docSubmissions;
    private ArrayList<Company> verifiedCompanies;
    private AdminHandler handler;

    private String name;
    private String adminNumber;

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

    public AdminHandler getHandler() {
        return handler;
    }

    // TODO Networking implementation
    public ArrayList<String> recieveRequest() {
        return null;
    }

    // TODO Networking implementation
    public void sendOutcome(boolean status, String response) {
        AdminResponse res = new AdminResponse(status, response);
        try {
            handler.sendResponse(handler.processResponse(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
