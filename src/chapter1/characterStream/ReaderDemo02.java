package chapter1.characterStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * �ַ���Reader�����÷����������Ե����ַ�������������int�����������У�����÷���
 *  int len = 0;
 *  char[] chars = new char[1024];
 *  int temp;
 *  while ((temp = reader.read()) != -1) {   // ��ÿ�ζ�ȡ�����ݸ�temp�������������-1����ʾ�Ѿ���ȡ���
 *      chars[len++] = (char) temp;
 *  }
 */
public class ReaderDemo02 {
    public static void main(String[] args) throws IOException {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);

        Reader reader = new FileReader(f);

        int len = 0;
        char[] chars = new char[1024];
        int temp;

        while ((temp = reader.read()) != -1) {   // ��ÿ�ζ�ȡ�����ݸ�temp�������������-1����ʾ�Ѿ���ȡ���
            chars[len++] = (char) temp;
        }
        reader.close();

        System.out.println("�����ǣ�" + new String(chars, 0, len));
    }
}
