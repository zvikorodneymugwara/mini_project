package app.objects.course;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

public class Degree {
    private Blockchain<Module> degreeModules;
    private String degName;
    private String degCode;
    private Transaction<String> degreeVerifier;

    public Degree(String sNum, String degCode) {
        this.degreeModules = new Blockchain<Module>();
        this.degreeVerifier = new Transaction<String>(sNum, degCode, "");
    }

    public Degree() {
        this.degreeModules = new Blockchain<Module>();
        this.degreeVerifier = new Transaction<String>("", "", "");
    }

    public String getDegCode() {
        return degCode;
    }

    public String getDegName() {
        return degName;
    }

    public Blockchain<Module> getDegreeModules() {
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

    public void setDegreeModules(Blockchain<Module> degreeModules) {
        this.degreeModules = degreeModules;
    }

    public void setDegreeVerifier(Transaction<String> degreeVerifier) {
        this.degreeVerifier = degreeVerifier;
    }
}
