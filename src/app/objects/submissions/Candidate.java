package app.objects.submissions;

import java.io.Serializable;
import java.util.ArrayList;

import acsse.csc03a3.Block;
import acsse.csc03a3.Transaction;
import app.objects.Vote;

public class Candidate implements Serializable {
    private String candidateID;
    private String candidateName;
    /**
     * candidate has a block of votes where each vote is a transaction
     */
    private Block<Vote> votes;

    public Candidate() {
        this.votes = new Block<Vote>("", new ArrayList<>());
    }

    public Candidate(String id, String name) {
        this.candidateID = id;
        this.candidateName = name;
        this.votes = new Block<Vote>("", new ArrayList<>());
    }

    // getters and setters

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

    /**
     * adds a vote to the block of votes of the candidate if it is valid
     * 
     * @param vote
     * @return
     */
    public boolean addVote(Transaction<Vote> vote) {
        if (validateVote(vote)) {
            votes.getTransactions().add(vote);
            return true;
        }
        return false;
    }

    /**
     * validates the given vote
     * 
     * @param vote
     * @return
     */
    private boolean validateVote(Transaction<Vote> vote) {
        // send if the vote has not already been submitted
        for (Transaction<Vote> entry : votes.getTransactions()) {
            if (vote.getSender().equals(entry.getSender())) {
                return false;
            }
        }
        return true;
    }
}
