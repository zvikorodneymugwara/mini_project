package app.objects.course;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;

public class DegreeModule {
    private int numAssessments;
    private boolean examEntrance;
    private Block<Transaction<Number>> assessments;
    private String status;
    private String moduleCode;

    public DegreeModule() {
    }

    public DegreeModule(int numAssessments, String status, String moduleCode) {
        this.numAssessments = numAssessments;
        this.status = status;
        this.moduleCode = moduleCode;
    }

    public Block<Transaction<Number>> getAssessments() {
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

    public void setAssessments(Block<Transaction<Number>> assessments) {
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
}