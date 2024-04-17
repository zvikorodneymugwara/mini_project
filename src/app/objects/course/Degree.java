package app.objects.course;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import java.util.ArrayList;

public class Degree {
    private Block<DegreeModule> degreeModules;
    private String degName;
    private String degCode;
    private Transaction<String> degreeVerifier;

    public Degree(String sNum, String degCode) {
        this.degreeModules = new Block<DegreeModule>("", new ArrayList<>());
        this.degreeVerifier = new Transaction<String>(sNum, degCode, "In Progress");
    }

    public Degree() {
        this.degreeModules = new Block<DegreeModule>("", new ArrayList<>());
        this.degreeVerifier = new Transaction<String>("", "", "");
    }

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
}
