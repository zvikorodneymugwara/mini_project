package app.objects;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.network.AdminHandler;
import app.network.ClientHandler;
import app.network.UserHandler;
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
    private int userType;

    // handler will handle all network communications
    private UserHandler handler;

    public SystemUser(String name, String studentNumber, int userType) {
        this.userType = userType;
        this.name = name;
        this.studentNumber = studentNumber;
        this.submissions = new Block<SubmissionDocument>("", new ArrayList<>());
        this.notices = new ArrayList<>();
        this.degree = new Degree(studentNumber, "B2I02Q", "Computer Science and Informatics", "Science", 360);
        currentYear = 2;
        degree.getDegreeModules().getTransactions()
                .add(new Transaction<DegreeModule>(studentNumber, "UJ", new DegreeModule(3, "In Progress", "IFM02A2")));
        degree.getDegreeModules().getTransactions()
                .add(new Transaction<DegreeModule>(studentNumber, "UJ", new DegreeModule(3, "In Progress", "CSC02A2")));
        degree.getDegreeModules().getTransactions()
                .add(new Transaction<DegreeModule>(studentNumber, "UJ", new DegreeModule(3, "In Progress", "ETNEE2A")));
        for (Transaction<DegreeModule> module : degree.getDegreeModules().getTransactions()) {
            Block<Number> block = new Block<Number>("", new ArrayList<>());
            block.getTransactions().add(new Transaction<Number>("", "", new Random().nextInt(40, 99)));
            block.getTransactions().add(new Transaction<Number>("", "", new Random().nextInt(40, 99)));
            block.getTransactions().add(new Transaction<Number>("", "", new Random().nextInt(40, 99)));
            module.getData().setAssessments(block);
        }
        try {
            if (userType == 0) {
                handler = new ClientHandler(new Socket("localhost", 2021));
            } else {
                handler = new AdminHandler(new Socket("localhost", 2021));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getUserType() {
        return userType;
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

    public UserHandler getHandler() {
        return handler;
    }
}
