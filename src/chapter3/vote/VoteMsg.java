package chapter3.vote;

/**
 * Created by Administrator on 2017/12/30.
 */
public class VoteMsg {
    private boolean isInquiry;  // true if inquiry; false if vote
    private boolean isResponse; // true if response from server
    private int candidateID;    // in [0, 1000]
    private long voteCount;     // nonzero only in response

    public static final int MAX_CANDIDATE_ID = 1000;

    public VoteMsg(boolean isResponse, boolean isInquiry, int candidateID, long voteCount)
        throws IllegalArgumentException {
        // check invariants
        if (voteCount != 0 && !isResponse) {
            throw new IllegalArgumentException("Request vote count must be zero");
        }
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad Candidate ID : " + candidateID);
        }
        if (voteCount < 0) {
            throw new IllegalArgumentException("Total must be >= zero");
        }
        this.candidateID = candidateID;
        this.isInquiry = isInquiry;
        this.isResponse = isResponse;
        this.voteCount = voteCount;
    }

    public boolean isInquiry() {
        return isInquiry;
    }

    public void setInquiry(boolean inquiry) {
        isInquiry = inquiry;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setResponse(boolean response) {
        isResponse = response;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad Candidate ID : " + candidateID);
        }
        this.candidateID = candidateID;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        if ((voteCount != 0 && !isResponse) || voteCount < 0) {
            throw new IllegalArgumentException("Bad vote count");
        }
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        String res = (isInquiry ? "inquiry" : "vote") + " for candidate " + candidateID;
        if (isResponse) {
            res = "response to " + res + "who now has " + voteCount + " vote(s)";
        }
        return res;
    }
}
