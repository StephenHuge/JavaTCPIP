package chapter3.vote.coder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import chapter3.vote.VoteMsg;

/**
 * 基于文本的编解码工具
 * Created by Administrator on 2017/12/30.
 */
public class VoteMsgTextCoder implements VoteMsgCoder {
    /**
     * 电报格式 "VOTEPROTO" <"v"|"i"> [<RESPFLAG>] <CANDIDATE> [<VOTECNT>]
     * 字符集将会通过电报格式修复。
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
	 * 根据特定协议，将投票消息转换成一个字节序列
	 * 信息转化为byte字节 
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
     * 根据特定协议，将一个字节序列转换成投票消息
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
                throw new IOException("错误的魔数: " + token);
            }
            token = s.next();
            if (token.equals(VOTESTR)) {
                isInquiry = false;
            } else if (!token.equals(INQSTR)) {
                throw new IOException("错误的查询/投票标识:" + token);
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
            // 当前的token是参赛者的ID
            // Note: isResponse 现在是有效的
            candidateID = Integer.parseInt(token);
            if (isResponse) {
                token = s.next();
                voteCount = Long.parseLong(token);
            } else {
                voteCount = 0;
            }
        } catch (IOException ioe) {
            throw new IOException("解析错误...");
        }
        return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
    }
}
