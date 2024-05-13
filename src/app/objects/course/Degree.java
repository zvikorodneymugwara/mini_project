package app.objects.course;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import java.util.ArrayList;

/**
 * Degree object
 */
public class Degree {
    //stores a block of degree modules
    private Block<DegreeModule> degreeModules;
    private String degName;
    private String degCode;
    private String facutly;
    private int credits;
    //stores a transaction of the student number and the degree code and the status of the degree verification
    private Transaction<String> degreeVerifier;

    /**
     * constructor
     * @param sNum
     * @param degCode
     * @param degName
     * @param facutly
     * @param credits
     */
    public Degree(String sNum, String degCode, String degName, String facutly, int credits) {
        this.degreeModules = new Block<DegreeModule>("", new ArrayList<>());
        this.degreeVerifier = new Transaction<String>(sNum, degCode, "In Progress");
        this.facutly = facutly;
        this.credits = credits;
        this.degCode = degCode;
        this.degName = degName;
    }

    public Degree() {
        this.degreeModules = new Block<DegreeModule>("", new ArrayList<>());
        this.degreeVerifier = new Transaction<String>("", "", "");
    }

    //getters and setters

    public String getDegCode() {
        return degCode;
    }

    public String getDegName() {
        return degName;
    }

    public Block<DegreeModule> getDegreeModules() {
        return degreeModules;
    }

    public Transaction<String> getDegreeVerifier() {
        return degreeVerifier;
    }

    public void setDegCode(String degCode) {
        this.degCode = degCode;
    }

    public void setDegName(String degName) {
        this.degName = degName;
    }

    public void setDegreeModules(Block<DegreeModule> degreeModules) {
        this.degreeModules = degreeModules;
    }

    public void setDegreeVerifier(Transaction<String> degreeVerifier) {
        this.degreeVerifier = degreeVerifier;
    }

    public String getFaculty() {
        return facutly;
    }

    public int getCredits() {
        return credits;
    }
}
