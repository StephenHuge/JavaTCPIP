package chapter1.file;

import java.io.File;

/**
 * 创建文件夹
 */
public class FileDemo07 {
    public static void main(String[] args) {
        String dir = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "TestDir";
        File f = new File(dir);
        f.mkdir();
    }
}
