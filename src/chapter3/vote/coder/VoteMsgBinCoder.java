package chapter3.vote.coder;

import java.io.*;

import chapter3.vote.VoteMsg;

/**
 * �����ֽڵı���빤��
 * 
 * Created by Administrator on 2017/12/30.
 * Wire Format
 *                               1  1  1  1  1  1
 * 0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |     Magic       |Flags|        ZERO        |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |               CandidateID                  |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                                            |
 * |       Vote Count (Only in response)        |
 * |                                            |
 * |                                            |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 */
public class VoteMsgBinCoder implements VoteMsgCoder {

    // �����õĳ����嵥
    public static int MIN_WIRE_LENGTH = 4;
    public static int MAX_WIRE_LENGTH = 16;
    public static int MAGIC = 0x5400;
    public static int MAGIC_MASK = 0xfc00;
    public static int MAGIC_SHIFT = 8;
    public static int RESPONSE_FLAG = 0x0200;
    public static int INQUIRY_FLAG = 0x0100;

    /**
	 * �����ض�Э�飬��ͶƱ��Ϣת����һ���ֽ����� 
	 */
    public byte[] toWire(VoteMsg msg) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteStream);    // converts ints

        short magicAndFlags = (short) (MAGIC & 0xffff);
        if (msg.isInquiry()) {
            magicAndFlags |= INQUIRY_FLAG;
        }
        if (msg.isResponse()) {
            magicAndFlags |= RESPONSE_FLAG;
        }
        out.writeShort(magicAndFlags);
        // ��ѡ�ߵ� ID ���Դ���һ��short����: ����ֵ >0 && < 1000
        out.writeShort((short) msg.getCandidateID());
        if (msg.isResponse()) {
            out.writeLong(msg.getVoteCount());
        }
        out.flush();
        byte[] data = byteStream.toByteArray();
        return data;
    }
    
    /**
     * �����ض�Э�飬��һ���ֽ�����ת����ͶƱ��Ϣ
     */ 
    public VoteMsg fromWire(byte[] input) throws IOException {
        // �����Լ��
        if (input.length < MIN_WIRE_LENGTH) {
            throw new IOException("��Ϣ��ȫ��");
        }
        ByteArrayInputStream bs = new ByteArrayInputStream(input);
        DataInputStream in = new DataInputStream(bs);
        int magic = in.readShort();
        if ((magic & MAGIC_MASK) != MAGIC) {
            throw new IOException("Bad Magic #: " +
                    ((magic & MAGIC_MASK) >> MAGIC_SHIFT));
        }
        boolean resp = ((magic & RESPONSE_FLAG) != 0);
        boolean inq = ((magic & INQUIRY_FLAG) != 0);
        int candidateID = in.readShort();
        if (candidateID < 0 || candidateID > 1000) {
            throw new IOException("Bad candidate ID:" + candidateID);
        }
        long count = 0;
        if (resp) {
            count = in.readLong();
            if (count < 0) {
                throw new IOException("Bad vote count: " + count);
            }
        }
        // Ingore any extra bytes
        return new VoteMsg(resp, inq, candidateID, count);
    }

}
