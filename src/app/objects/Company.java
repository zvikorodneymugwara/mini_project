package app.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.objects.submissions.SubmissionDocument;

public class Company implements Serializable {
    private String companyNumber;
    private String companyName;
    private Block<SubmissionDocument> distributedNotes;

    public Company() {

    }

    public Company(String companyNum, String compName) {
        this.companyNumber = companyNum;
        this.companyName = compName;
    }

    @Override
    public String toString() {
        return "Company Name: " + this.companyName + ", Company Numebr:" + this.companyNumber;
    }

    public void initializeDistributedNotes() {
        List<Transaction<SubmissionDocument>> list = new ArrayList<Transaction<SubmissionDocument>>();
        this.distributedNotes = new Block<SubmissionDocument>(companyNumber + " " + companyName, list);
    }

    public boolean findNote(SubmissionDocument document) {
        return false;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public SubmissionDocument getDocument(String docId) {
        return null;
    }

    public Block<SubmissionDocument> getDistributedNotes() {
        return distributedNotes;
    }
}
