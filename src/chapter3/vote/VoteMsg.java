package chapter3.vote;

/**
 * Created by Administrator on 2017/12/30.
 */
public class VoteMsg {
    private boolean isInquiry;  // �����ѯΪtrue; ͶƱ��Ϊfalse
    private boolean isResponse; // ����յ���������Ӧ�򷵻�true 
    private int candidateID;    // ��Χ [0, 1000]
    private long voteCount;     // ֻ������Ӧ�в�Ϊ0

    public static final int MAX_CANDIDATE_ID = 1000;

    public VoteMsg(boolean isResponse, boolean isInquiry, int candidateID, long voteCount)
        throws IllegalArgumentException {
        // ��ⲻ����
        if (voteCount != 0 && !isResponse) {
            throw new IllegalArgumentException("�����е�ͶƱ������Ϊ0");
        }
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("����Ĳ����� ID : " + candidateID);
        }
        if (voteCount < 0) {
            throw new IllegalArgumentException("ͶƱ�������� >= 0");
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
            throw new IllegalArgumentException("����Ĳ����� ID : " + candidateID);
        }
        this.candidateID = candidateID;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        if ((voteCount != 0 && !isResponse) || voteCount < 0) {
            throw new IllegalArgumentException("�����Ʊ��");
        }
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        String res = (isInquiry ? "��ѯ" : "ͶƱ") + " ���������� " + candidateID;
        if (isResponse) {
            res = "��Ӧ�� " + res + "������" + voteCount + " ��Ʊ";
        }
        return res;
    }
}
