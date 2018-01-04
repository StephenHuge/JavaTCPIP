package chapter3.vote;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/30.
 */
public interface VoteMsgCoder {
    byte[] toWire(VoteMsg msg) throws IOException;
    VoteMsg fromWire(byte[] input) throws IOException;
}
