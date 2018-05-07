package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * IuputStream�����÷�������read������ȡ�����ֽ�
 * byte[] b = new byte[(int) f.length()];  // �������ļ���С����
 * for (int i = 0; i < b.length; i++)
 *    b[i] = (byte) in.read();        // �����ֽڶ�ȡ
 */
public class InputStreamDemo03 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[(int) f.length()];  // �������ļ���С����

        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) in.read();        // �����ֽڶ�ȡ
        }
        in.close();

        System.out.println("�����ǣ�" + new String(b));
    }
}
