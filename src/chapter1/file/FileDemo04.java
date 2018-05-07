package chapter1.file;

import java.io.File;

/**
 * 删除文件
 */
public class FileDemo04 {
    public static void main(String[] args) {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        if (f.exists()) {   // 判断文件是否存在
            f.delete();
        }
    }
}
