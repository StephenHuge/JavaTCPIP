package chapter1.characterStream;

import java.io.*;

/**
 * �ַ���Reader�����÷��������ݶ���������
 *  char[] chars = new char[1024];
 *  reader.read(chars);
 */
public class ReaderDemo01 {
    public static void main(String[] args) throws IOException {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);

        Reader reader = new FileReader(f);
        char[] chars = new char[1024];

        int len = reader.read(chars);
        reader.close();

        System.out.println("�����ǣ�" + new String(chars, 0, len));
    }
}
