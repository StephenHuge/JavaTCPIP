package chapter3.others;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * ʹ�ñ������б������
 */
public class BruteForceCoding {
	private static byte byteVal = 101; 	// 1����1
	private static short shortVal = 10001;	// 1����1
	private static int intVal = 100000001;	// 1����1
	private static long longVal = 1000000000001L;	// 1������1
	
	private final static int BSIZE = Byte.SIZE 		>> 3;		// 1Byte��ռ���ֽ���
	private final static int SSIZE = Short.SIZE 	>> 3;
	private final static int ISIZE = Integer.SIZE	>> 3;
	private final static int LSIZE = Long.SIZE 		>> 3;
	
	private final static int BYTEMASK = 0xFF;		// 8 bits
	
	/**
	 * ��byte[]����ת��Ϊʮ���Ʊ�ʾ��String
	 * 
	 * @param bArray ����byte[]����
	 * @return ת�����
	 */
	public static String byteArrayToDecimalString(byte[] bArray) {
		StringBuilder rtn = new StringBuilder();
		for (byte b : bArray) {
			rtn.append(b & BYTEMASK).append(" ");	// �˴�ʹ��������Ϊ�˷�ֹ�ֽ���ֵת��Ϊint����ʱ������������չ����ת�����޷�������
		}
		return rtn.toString();
	}
	
	/**
	 * ��long�����ֽ��б��룬�������浽byte[]������
	 * Warning: δ�Գ�ʼ�������в��� (e.g. 0 <= size <= 8)
	 *  
	 * @param dst ���봢���Ŀ������
	 * @param val Ҫ���б��������
	 * @param offset ƫ������������ĵ� (offset + 1) λ��ʼ���� 
	 * @param size �����λ��
	 * @return ������ɺ��ƫ����
	 */
	public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
		for (int i = 0; i < size; i++) {
			dst[offset++] = (byte) (val >> ((size - i - 1) * Byte.SIZE));
		}
		return offset;
	}
	/**
	 * ��byte[]������н��룬������Ϊlong�� 
	 * Warning: δ�Գ�ʼ�������в��� (e.g. 0 <= size <= 8) 
	 */
	public static long decodeIntBigEndian(byte[] val, int offset, int size) {
		long rtn = 0;
		for (int i = 0; i < size; i++) {
			rtn = (rtn << Byte.SIZE) | ((long) val[offset + i] & BYTEMASK);	// �ӺŵĻ����������з����������
		}
		return rtn;
	}
	
	public static void main(String[] args) {
		byte[] message = new byte[BSIZE + SSIZE + ISIZE + LSIZE];
		// �������������뵽Ŀ��������
		
		int offset = encodeIntBigEndian(message, byteVal, 0, BSIZE);
		offset = encodeIntBigEndian(message, shortVal, offset, SSIZE);
		offset = encodeIntBigEndian(message, intVal, offset, ISIZE);
		encodeIntBigEndian(message, longVal, offset, LSIZE);
		System.out.println("���������ı�����Ϊ: \t\t" + byteArrayToDecimalString(message));
		/************************************************************************
		 * �����湦����ͬ
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
        System.out.println("ʹ��ϵͳI/O�����б�����Ϊ: \t" + byteArrayToDecimalString(msg));
        System.out.println();
        
        // ����������
		long value = decodeIntBigEndian(message, BSIZE, SSIZE);
		System.out.println("�� short ���н��� = " + value);
		value = decodeIntBigEndian(message, BSIZE + SSIZE + ISIZE, LSIZE);
		System.out.println("�� long ���н��� = " + value);
		
		// Demonstrate dangers of conversion
		offset = 4;
		value = decodeIntBigEndian(message, offset, BSIZE);
		System.out.println("Decode value (offset " + offset + ", size " + BSIZE + ") = "
				+ value);
		byte bVal = (byte) decodeIntBigEndian(message, offset, BSIZE);
		System.out.println("Same value as byte = " + bVal);
		
	}
}
