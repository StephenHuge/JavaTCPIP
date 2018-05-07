package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream�����÷���Ҳ����õ��÷���
 * ����code template:
 *
 * InputStream in;
 * int a;
 *
 * // read()����ֵ�Ƕ�ȡ���ֽ�ֵ��������int�����������-1�����ʾ��ȡ���
 * while ((a = in.read()) != -1) {
 *  // do sth
 * }
 *
 */
public class InputStreamDemo04 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);

        int len = 0;
        byte[] b = new byte[1024];
        int temp;

        while ((temp = in.read()) != -1) {  // ��ÿ�ζ�ȡ��������temp����ȡ����򷵻�-1
            b[len] = (byte) temp;
            len++;
        }
        in.close();

        System.out.println("�����ǣ�" + new String(b, 0, len));
    }
}
