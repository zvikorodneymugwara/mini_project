package app.objects;

import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.Notice;
import app.objects.course.Degree;
import app.objects.course.DegreeModule;
import app.objects.submissions.SubmissionDocument;

public class SystemUser {
    // list of all transactions this user has made
    private Block<SubmissionDocument> submissions;
    private Degree degree;
    private ArrayList<Notice> notices;
    private int currentYear;
    private String name;
    private String studentNumber;

    public SystemUser(String name, String studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.submissions = new Block<SubmissionDocument>("", new ArrayList<>());
        this.notices = new ArrayList<>();
        this.degree = new Degree(studentNumber, "B2I02Q");
        currentYear = 2;
        degree.getDegreeModules().getTransactions()
                .add(new Transaction<DegreeModule>(studentNumber, "UJ", new DegreeModule(3, "In Progress", "IFM02A2")));
        degree.getDegreeModules().getTransactions()
                .add(new Transaction<DegreeModule>(studentNumber, "UJ", new DegreeModule(3, "In Progress", "CSC02A2")));
        degree.getDegreeModules().getTransactions()
                .add(new Transaction<DegreeModule>(studentNumber, "UJ", new DegreeModule(3, "In Progress", "ETNEE2A")));
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public Block<SubmissionDocument> getSubmissions() {
        return submissions;
    }

    public void addNotice(Notice notice) {
        notices.add(notice);
    }

    public ArrayList<Notice> getNotices() {
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

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    // TODO networking implementation later
    public ArrayList<String> recieveOutcomes() {
        return null;
    }
}
