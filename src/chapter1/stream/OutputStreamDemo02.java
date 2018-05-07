package chapter1.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * OutputStream基本用法，区别是这个可以在文件后面继续追加。
 *  OutputStream out = new FileOutputStream(f, true);
 */
public class OutputStreamDemo02 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        // 和demo1的唯一区别，可以在文件后继续追加内容
        OutputStream out = new FileOutputStream(f, true);

        String str = "hello world!";
        byte[] b = str.getBytes();

        out.write(b);   // 将内容输出到文件

        for (int i = 0; i < b.length; i++) {
            out.write(b[i]);        // 和上面的write方法最终结果一致
        }
        out.close();
    }
}
