package app.objects.submissions;

public class Affidavit extends SubmissionDocument {

    public Affidavit(String docID, String issueDate, String regNumber, String docInfo, String studentNumber) {
        super(docID, issueDate, regNumber, docInfo, studentNumber);
    }

    public Affidavit() {
        super();
    }

    private String employeeID;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
