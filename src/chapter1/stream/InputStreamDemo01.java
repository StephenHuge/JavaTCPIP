package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream �����÷���ֱ�Ӷ�ȡ��������
 *  byte[] b = new byte[1024];
 *  in.read(b);
 *
 */
public class InputStreamDemo01 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];

        int len = in.read(b);       // �����ݶ������飬����ֵ�Ƕ������ݵĳ���

        in.close();
        System.out.println("�������ݵĳ��ȣ�" + len);
        System.out.println("�����ǣ�" + new String(b, 0, len)); // ����ָ����Χ����Ȼ���ӡ1024���ֽ�
    }
}
