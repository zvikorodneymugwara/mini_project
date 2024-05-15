package app.objects.course;

import java.util.List;

import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

/**
 * Degree modules class
 */
public class DegreeModule {
    private int numAssessments; // number of assessments
    private boolean examEntrance;

    /*
     * block of assessments holds transactions which will be the marks of each
     * assessment
     */
    private Block<Number> assessments;
    private String status;
    private String moduleCode;
    private Blockchain<Number> assessmentBlockchain;

    public DegreeModule() {
        assessmentBlockchain = new Blockchain<>();
        assessmentBlockchain.registerStake("201100101", 10);
    }
    
    public DegreeModule(int numAssessments, String status, String moduleCode) {
        this.numAssessments = numAssessments;
        this.status = status;
        this.moduleCode = moduleCode;
        assessmentBlockchain = new Blockchain<>();
        assessmentBlockchain.registerStake("201100101", 10);
    }
    // getters and setters

    public Block<Number> getAssessments() {
        return assessments;
    }

    public int getNumAssessments() {
        return numAssessments;
    }

    public String getStatus() {
        return status;
    }

    public boolean hasExamEntrance() {
        return examEntrance;
    }

    public void setAssessments(Block<Number> assessments) {
        this.assessments = assessments;
    }

    public void setExamEntrance(boolean examEntrance) {
        this.examEntrance = examEntrance;
    }

    public void setNumAssessments(int numAssessments) {
        this.numAssessments = numAssessments;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Adds the assessment marks to the module's assessment blockchain
     * @param assessments
     */
    public void addAssessmentsToBlockChain(List<Transaction<Number>> assessments){
        assessmentBlockchain.addBlock(assessments);
    }
}
