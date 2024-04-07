package app.objects.submissions;

import java.io.Serializable;

public abstract class SubmissionDocument implements Serializable {
    protected String docID;
    protected String issueDate;
    protected String regNumber;
    protected String docInfo;
    protected String studentNumber;

    public SubmissionDocument(String docID, String issueDate, String regNumber, String docInfo, String studentNumber) {
        this.docID = docID;
        this.issueDate = issueDate;
        this.regNumber = regNumber;
        this.docInfo = docInfo;
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getDocID() {
        return docID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getDocInfo() {
        return docInfo;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }
}
