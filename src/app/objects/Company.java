package app.objects;

import java.io.Serializable;

import acsse.csc03a3.Block;
import app.objects.submissions.SubmissionDocument;

public class Company implements Serializable{
    private String companyNumber;
    private Block<SubmissionDocument> distributedNotes;

    public boolean findNote(SubmissionDocument document){
        return false;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public SubmissionDocument getDocument(String docId){
        return null;
    }
}
