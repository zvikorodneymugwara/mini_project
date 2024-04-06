package app.objects.submissions;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;

public class Candidate {
    private String candidateID;
    private String candidateName;
    private Block<String> votes;

    public String getCandidateName() {
        return candidateName;
    }
    private boolean validateVote(Transaction<String> vote){
        return false;
    }
}
