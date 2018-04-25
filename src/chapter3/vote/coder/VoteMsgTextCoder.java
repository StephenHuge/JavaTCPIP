package chapter3.vote.coder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import chapter3.vote.VoteMsg;

/**
 * �����ı��ı���빤��
 * Created by Administrator on 2017/12/30.
 */
public class VoteMsgTextCoder implements VoteMsgCoder {
    /**
     * �籨��ʽ "VOTEPROTO" <"v"|"i"> [<RESPFLAG>] <CANDIDATE> [<VOTECNT>]
     * �ַ�������ͨ���籨��ʽ�޸���
     */
    // Manifest constants for encoding
    public static final String MAGIC = "Voting";
    public static final String VOTESTR = "v";
    public static final String INQSTR = "i";
    public static final String RESPONSESTR = "R";

    public static final String CHARSETNAME = "US-ASCII";
    public static final String DELIMSTR = " ";
    public static final int MAX_WIRE_LENGTH = 2000;

    
    /**
	 * �����ض�Э�飬��ͶƱ��Ϣת����һ���ֽ�����
	 * ��Ϣת��Ϊbyte�ֽ� 
	 */
    public byte[] toWire(VoteMsg msg) throws IOException {
        String msgString = MAGIC + DELIMSTR + (msg.isInquiry() ? INQSTR : VOTESTR)
                + DELIMSTR + (msg.isResponse() ? (RESPONSESTR + DELIMSTR) : "")
                + Integer.toString(msg.getCandidateID()) + DELIMSTR
                + Long.toString(msg.getVoteCount());
        byte[] data = msgString.getBytes(CHARSETNAME);
        return data;
    }
    
    /**
     * �����ض�Э�飬��һ���ֽ�����ת����ͶƱ��Ϣ
     */ 
    public VoteMsg fromWire(byte[] message) throws IOException {
        ByteArrayInputStream msgStream = new ByteArrayInputStream(message);
        Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));
        boolean isInquiry;
        boolean isResponse;
        int candidateID;
        long voteCount;
        String token;

        try {
            token = s.next();
            if (!token.equals(MAGIC)) {
                throw new IOException("�����ħ��: " + token);
            }
            token = s.next();
            if (token.equals(VOTESTR)) {
                isInquiry = false;
            } else if (!token.equals(INQSTR)) {
                throw new IOException("����Ĳ�ѯ/ͶƱ��ʶ:" + token);
            } else {
                isInquiry = true;
            }

            token = s.next();
            if (token.equals(RESPONSESTR)) {
                isResponse = true;
                token = s.next();
            } else {
                isResponse = false;
            }
            // ��ǰ��token�ǲ����ߵ�ID
            // Note: isResponse ��������Ч��
            candidateID = Integer.parseInt(token);
            if (isResponse) {
                token = s.next();
                voteCount = Long.parseLong(token);
            } else {
                voteCount = 0;
            }
        } catch (IOException ioe) {
            throw new IOException("��������...");
        }
        return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
    }
}
