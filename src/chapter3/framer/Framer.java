package chapter3.framer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 成帧和解析。
 * 
 * Created by Administrator on 2017/12/30.
 */
public interface Framer {
	
	/**
	 * 添加成帧信息并将指定消息输出到指定流 
	 */
	void frameMsg(byte[] message, OutputStream out) throws IOException;
	
	/**
	 * 扫描指定流，从中抽取出下一条信息 
	 */
	byte[] nextMsg() throws IOException;
}
