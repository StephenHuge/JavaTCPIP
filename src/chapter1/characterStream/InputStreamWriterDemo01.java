package chapter1.characterStream;

import chapter1.constant.IOConstants;

import java.io.*;

/**
 * 将字节流转化为字符流输入
 */
public class InputStreamWriterDemo01 {
    public static void main(String[] args) throws IOException {
        File f = new File(IOConstants.PATH_TEST_TXT);
        Reader reader = new InputStreamReader(new FileInputStream(f));

        char[] chars = new char[1024];

        int len = reader.read(chars);
        reader.close();

        System.out.println(new String(chars, 0, len));
    }
}
