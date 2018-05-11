package chapter1.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * InputStream基本操作，区别是数组的大小由文件大小决定
 */
public class InputStreamDemo02 {
    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[(int) f.length()];  // 数组由文件大小决定

        in.read(b);       // 将数据读入数组
        in.close();

        System.out.println("内容是：" + new String(b));
    }
}
