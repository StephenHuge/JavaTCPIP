package chapter3.vote.coder;

import java.io.IOException;

import chapter3.vote.VoteMsg;

/**
 * ͶƱ��Ϣ�������Ľӿ�
 * Created by Administrator on 2017/12/30.
 */
public interface VoteMsgCoder {
	
	/**
	 * �����ض�Э�飬��ͶƱ��Ϣת����һ���ֽ����� 
	 */
    byte[] toWire(VoteMsg msg) throws IOException;
    
    /**
     * �����ض�Э�飬��һ���ֽ�����ת����ͶƱ��Ϣ
     */ 
    VoteMsg fromWire(byte[] input) throws IOException;
}
