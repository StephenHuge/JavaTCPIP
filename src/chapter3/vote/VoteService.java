package chapter3.vote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/30.
 */
public class VoteService {
    // Map of candidates to numbers of votes
    private Map<Integer, Long> results = new HashMap<>();

    public VoteMsg handleRequest(VoteMsg msg) {
        if (msg.isResponse()) { // If response, just send it back
            return msg;
        }
        msg.setResponse(true);  // Make message a response
        // Get candidate ID and vote count
        int candidate = msg.getCandidateID();
        Long count = results.get(candidate);
        if (count == null) {
            count = 0L; // Candidate does not exist
        }
        if (!msg.isInquiry()) {
            results.put(candidate, ++count);    // If vote, increment count
        }
        msg.setVoteCount(count);
        return msg;
    }
}
