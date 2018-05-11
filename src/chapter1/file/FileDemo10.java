package chapter1.file;

import java.io.File;
import java.io.IOException;

/**
 * 列出指定目录下所有文件
 */
public class FileDemo10 {
    public static void main(String[] args) throws IOException {
        String dir = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes";
        File f = new File(dir);
        print(f);
    }
    public static void print(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                System.out.println(file);
                for (File f : files) {
                    if (f != null)  print(f);   // 递归调用print()方法打印
                }
            } else {
                System.out.println(" " + file);
            }
        }
    }
}
