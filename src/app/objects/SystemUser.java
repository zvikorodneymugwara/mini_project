package app.objects;

import java.util.ArrayList;

import acsse.csc03a3.Block;
import app.objects.submissions.SubmissionDocument;

public class SystemUser {
    // list of all transactions this user has made
    private Block<SubmissionDocument> submissions;
    private String name;
    private String studentNumber;
    private ArrayList<String> notices;

    public SystemUser(String name, String studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.submissions = new Block<SubmissionDocument>("", new ArrayList<>());
        this.notices = new  ArrayList<>();
    }

    public Block<SubmissionDocument> getSubmissions() {
        return submissions;
    }

    public ArrayList<String> getNotices() {
        return notices;
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
}
