package chapter1.systemClass;

import chapter1.constant.IOConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 设置System.in的重定向。
 * 本来从键盘读取输入 -> 从文件读取。
 */
public class SystemDemo08 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(IOConstants.PATH_TEST_TXT);
        System.setIn(fileInputStream);
        InputStream in = System.in;
        byte[] bytes = new byte[1024];
        int len = in.read(bytes);

        System.out.println(new String(bytes, 0, len));
    }
}
