package chapter3.others;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 使用暴力进行编码解码
 */
public class BruteForceCoding {
	private static byte byteVal = 101; 	// 1百零1
	private static short shortVal = 10001;	// 1万零1
	private static int intVal = 100000001;	// 1亿零1
	private static long longVal = 1000000000001L;	// 1万亿零1
	
	private final static int BSIZE = Byte.SIZE 		>> 3;		// 1Byte所占的字节数
	private final static int SSIZE = Short.SIZE 	>> 3;
	private final static int ISIZE = Integer.SIZE	>> 3;
	private final static int LSIZE = Long.SIZE 		>> 3;
	
	private final static int BYTEMASK = 0xFF;		// 8 bits
	
	/**
	 * 将byte[]数组转化为十进制表示的String
	 * 
	 * @param bArray 输入byte[]数组
	 * @return 转化结果
	 */
	public static String byteArrayToDecimalString(byte[] bArray) {
		StringBuilder rtn = new StringBuilder();
		for (byte b : bArray) {
			rtn.append(b & BYTEMASK).append(" ");	// 此处使用掩码是为了防止字节数值转换为int类型时，发生符号扩展，即转换成无符号整数
		}
		return rtn.toString();
	}
	
	/**
	 * 对long型数字进行编码，编码结果存到byte[]数组中
	 * Warning: 未对初始条件进行测试 (e.g. 0 <= size <= 8)
	 *  
	 * @param dst 编码储存的目标数组
	 * @param val 要进行编码的数据
	 * @param offset 偏移量，从数组的第 (offset + 1) 位开始操作 
	 * @param size 编码的位数
	 * @return 编码完成后的偏移量
	 */
	public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
		for (int i = 0; i < size; i++) {
			dst[offset++] = (byte) (val >> ((size - i - 1) * Byte.SIZE));
		}
		return offset;
	}
	/**
	 * 对byte[]数组进行解码，解码结果为long型 
	 * Warning: 未对初始条件进行测试 (e.g. 0 <= size <= 8) 
	 */
	public static long decodeIntBigEndian(byte[] val, int offset, int size) {
		long rtn = 0;
		for (int i = 0; i < size; i++) {
			rtn = (rtn << Byte.SIZE) | ((long) val[offset + i] & BYTEMASK);	// 加号的话会变成两个有符号数的相加
		}
		return rtn;
	}
	
	public static void main(String[] args) {
		byte[] message = new byte[BSIZE + SSIZE + ISIZE + LSIZE];
		// 将三个变量编码到目标数组中
		
		int offset = encodeIntBigEndian(message, byteVal, 0, BSIZE);
		offset = encodeIntBigEndian(message, shortVal, offset, SSIZE);
		offset = encodeIntBigEndian(message, intVal, offset, ISIZE);
		encodeIntBigEndian(message, longVal, offset, LSIZE);
		System.out.println("对三个数的编码结果为: \t\t" + byteArrayToDecimalString(message));
		/************************************************************************
		 * 和上面功能相同
		 ************************************************************************/
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		DataOutputStream      out = new DataOutputStream(buf);
        try {
            out.writeByte(byteVal);
            out.writeShort(shortVal);
            out.writeInt(intVal);
            out.writeLong(longVal);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] msg = buf.toByteArray();
        System.out.println("使用系统I/O流进行编码结果为: \t" + byteArrayToDecimalString(msg));
        System.out.println();
        
        // 解码多个数据
		long value = decodeIntBigEndian(message, BSIZE, SSIZE);
		System.out.println("对 short 进行解码 = " + value);
		value = decodeIntBigEndian(message, BSIZE + SSIZE + ISIZE, LSIZE);
		System.out.println("对 long 进行解码 = " + value);
		
		// Demonstrate dangers of conversion
		offset = 4;
		value = decodeIntBigEndian(message, offset, BSIZE);
		System.out.println("Decode value (offset " + offset + ", size " + BSIZE + ") = "
				+ value);
		byte bVal = (byte) decodeIntBigEndian(message, offset, BSIZE);
		System.out.println("Same value as byte = " + bVal);
		
	}
}
