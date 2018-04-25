package chapter3.vote.coder;

import java.io.IOException;

import chapter3.vote.VoteMsg;

/**
 * 投票信息编码解码的接口
 * Created by Administrator on 2017/12/30.
 */
public interface VoteMsgCoder {
	
	/**
	 * 根据特定协议，将投票消息转换成一个字节序列 
	 */
    byte[] toWire(VoteMsg msg) throws IOException;
    
    /**
     * 根据特定协议，将一个字节序列转换成投票消息
     */ 
    VoteMsg fromWire(byte[] input) throws IOException;
}
