package chapter3.vote;

/**
 * Created by Administrator on 2017/12/30.
 */
public class VoteMsg {
    private boolean isInquiry;  // 如果查询为true; 投票则为false
    private boolean isResponse; // 如果收到服务器响应则返回true 
    private int candidateID;    // 范围 [0, 1000]
    private long voteCount;     // 只有在响应中不为0

    public static final int MAX_CANDIDATE_ID = 1000;

    public VoteMsg(boolean isResponse, boolean isInquiry, int candidateID, long voteCount)
        throws IllegalArgumentException {
        // 检测不变量
        if (voteCount != 0 && !isResponse) {
            throw new IllegalArgumentException("请求中的投票数必须为0");
        }
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("错误的参与者 ID : " + candidateID);
        }
        if (voteCount < 0) {
            throw new IllegalArgumentException("投票总数必须 >= 0");
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
            throw new IllegalArgumentException("错误的参与者 ID : " + candidateID);
        }
        this.candidateID = candidateID;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        if ((voteCount != 0 && !isResponse) || voteCount < 0) {
            throw new IllegalArgumentException("错误的票数");
        }
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        String res = (isInquiry ? "查询" : "投票") + " ：给参与者 " + candidateID;
        if (isResponse) {
            res = "响应： " + res + "现在有" + voteCount + " 张票";
        }
        return res;
    }
}
