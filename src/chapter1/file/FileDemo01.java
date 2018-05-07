package chapter1.file;

import java.io.File;
import java.io.IOException;

/**
 * 创建新文件
 */
public class FileDemo01 {
    public static void main(String[] args) {
        File f = new File("/home/hjs/Codes/test.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
