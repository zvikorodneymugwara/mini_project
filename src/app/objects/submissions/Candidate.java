package app.objects.submissions;

import java.io.Serializable;
import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.Vote;

public class Candidate implements Serializable {
    private String candidateID;
    private String candidateName;
    private Block<Vote> votes;

    public Candidate() {
        this.votes = new Block<Vote>("", new ArrayList<>());
    }

    public Candidate(String id, String name) {
        this.candidateID = id;
        this.candidateName = name;
        this.votes = new Block<Vote>("", new ArrayList<>());
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public Block<Vote> getVotes() {
        return votes;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setVotes(Block<Vote> votes) {
        this.votes = votes;
    }

    public boolean addVote(Transaction<Vote> vote) {
        if (validateVote(vote)) {
            votes.getTransactions().add(vote);
            return true;
        }
        return false;
    }

    private boolean validateVote(Transaction<Vote> vote) {
        for (Transaction<Vote> entry : votes.getTransactions()) {
            if (vote.getSender().equals(entry.getSender())) {
                return false;
            }
        }
        return true;
    }
}
