package chapter1.characterStream;

import chapter1.constant.IOConstants;

import java.io.*;

/**
 * 将字节流转化为字符流输出
 */
public class OutputStreamWriterDemo01 {
    public static void main(String[] args) throws IOException {
        File f = new File(IOConstants.PATH_TEST_TXT);
        Writer writer = new OutputStreamWriter(new FileOutputStream(f));

        writer.write("hello world");

        writer.close();
    }
}
