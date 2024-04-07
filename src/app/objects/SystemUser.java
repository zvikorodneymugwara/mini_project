package app.objects;

import java.util.ArrayList;

import acsse.csc03a3.Block;
import app.objects.submissions.SubmissionDocument;

public class SystemUser<T> {
    // list of all transactions this user has made
    private Block<SubmissionDocument> submissions;
    private String name;
    private String studentNumber;
    private ArrayList<String> notices;

    public SystemUser() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    // get the transactions from a txt file
    public void saveTransactions() {

    }

    // get the transactions from a txt file
    public void getTransactions() {

    }

    public void performTransaction() {

    }
}
