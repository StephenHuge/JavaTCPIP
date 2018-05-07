package chapter1.file;

import java.io.File;
import java.io.IOException;

/**
 * 使用目录分隔符
 */
public class FileDemo03 {
    public static void main(String[] args) {
        String path = File.separator + "home"
                    + File.separator + "hjs"
                    + File.separator + "Codes"
                    + File.separator + "test.txt";
        File f = new File(path);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
