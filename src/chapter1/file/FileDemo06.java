package chapter1.file;

import java.io.File;
import java.io.IOException;

/**
 * 如果文件存在，则删除文件，不存在则创建新文件。
 */
public class FileDemo06 {
    public static void main(String[] args) {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
