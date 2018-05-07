package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream��������������������Ĵ�С���ļ���С����
 */
public class InputStreamDemo02 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[(int) f.length()];  // �������ļ���С����

        in.read(b);       // �����ݶ�������
        in.close();

        System.out.println("�����ǣ�" + new String(b));
    }
}
