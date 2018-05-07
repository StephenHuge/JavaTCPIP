package chapter1.characterStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 字符流Reader基本用法。将数据以单个字符（返回类型是int）读到数组中，最常用用法。
 *  int len = 0;
 *  char[] chars = new char[1024];
 *  int temp;
 *  while ((temp = reader.read()) != -1) {   // 将每次读取的内容给temp变量，如果返回-1，表示已经读取完毕
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

        while ((temp = reader.read()) != -1) {   // 将每次读取的内容给temp变量，如果返回-1，表示已经读取完毕
            chars[len++] = (char) temp;
        }
        reader.close();

        System.out.println("内容是：" + new String(chars, 0, len));
    }
}
