package app;

import app.objects.submissions.SubmissionDocument;

public class Vote extends SubmissionDocument {
    private String candidateName;
    private String candidateID;
    private String voterId;

    public Vote(){

    }

    public Vote(String candidateName, String candidateID){
        this.candidateName = candidateName;
        this.candidateID = candidateID;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public String getVoterId() {
        return voterId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
}
