package app.objects;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
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
    // the degree the user is doing
    private Degree degree;
    private int currentYear;
    private String name;
    private String studentNumber;
    private int userType;

    // the blockchains
    protected Blockchain<SubmissionDocument> submissionsBlockchain;
    protected Blockchain<Degree> degreeBlockchain;

    // handler will handle all network communications
    private UserHandler handler;

    public SystemUser() {
        createBlockchain();
    }

    public SystemUser(String name, String studentNumber, int userType) {
        createBlockchain();
        this.userType = userType;
        this.name = name;
        this.studentNumber = studentNumber;

        // only students need this data
        if (userType == 0) {
            this.submissions = new Block<SubmissionDocument>("", new ArrayList<>());
            this.degree = new Degree(studentNumber, "B2I02Q", "Computer Science and Informatics", "Science", 360);
            currentYear = 2;
            degree.getDegreeModules().getTransactions()
                    .add(new Transaction<DegreeModule>(studentNumber, "UJ",
                            new DegreeModule(3, "In Progress", "IFM02A2")));
            degree.getDegreeModules().getTransactions()
                    .add(new Transaction<DegreeModule>(studentNumber, "UJ",
                            new DegreeModule(3, "In Progress", "CSC02A2")));
            degree.getDegreeModules().getTransactions()
                    .add(new Transaction<DegreeModule>(studentNumber, "UJ",
                            new DegreeModule(3, "In Progress", "ETNEE2A")));
            for (Transaction<DegreeModule> module : degree.getDegreeModules().getTransactions()) {
                Block<Number> block = new Block<Number>("", new ArrayList<>());
                block.getTransactions().add(new Transaction<Number>(studentNumber, module.getData().getModuleCode(),
                        new Random().nextInt(40, 99)));
                block.getTransactions().add(new Transaction<Number>(studentNumber, module.getData().getModuleCode(),
                        new Random().nextInt(40, 99)));
                block.getTransactions().add(new Transaction<Number>(studentNumber, module.getData().getModuleCode(),
                        new Random().nextInt(40, 99)));
                module.getData().setAssessments(block);
                module.getData().addAssessmentsToBlockChain(block.getTransactions());
            }
        }
        // different handlers for different user types
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

    private void createBlockchain() {
        if (submissionsBlockchain == null) {
            submissionsBlockchain = new Blockchain<>();
            submissionsBlockchain.registerStake("201100101", 10);
        }
        if (degreeBlockchain == null) {
            degreeBlockchain = new Blockchain<>();
            degreeBlockchain.registerStake("201100101", 10);
        }
    }

    // getters and setters

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

    public void setHandler(AdminHandler handler) {
        this.handler = handler;
    }

    /**
     * adds the current document to the blockchain of submissions
     * 
     * @param doc
     */
    public void addSubmissionToBlockChain(SubmissionDocument doc) {
        submissions.getTransactions().add(new Transaction<SubmissionDocument>(studentNumber, "Admin", doc));
        submissionsBlockchain.addBlock(submissions.getTransactions());
    }

}
