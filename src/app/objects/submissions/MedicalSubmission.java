package app.objects.submissions;


public class MedicalSubmission extends SubmissionDocument{
    private boolean excused;
    private String medicalRequest;

    public MedicalSubmission(String docID, String issueDate, String regNumber, String docInfo, String studentNumber) {
        super(docID, issueDate, regNumber, docInfo, studentNumber);
    }

    public MedicalSubmission(){
        super();
    }

    public boolean isExcused() {
        return excused;
    }

    public void setExcused(boolean excused) {
        this.excused = excused;
    }

    public String getMedicalRequest() {
        return medicalRequest;
    }

    public void setMedicalRequest(String medicalRequest) {
        this.medicalRequest = medicalRequest;
    }
}
