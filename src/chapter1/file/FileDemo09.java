package chapter1.file;

import java.io.File;

/**
 * 判定路径是否为目录
 */
public class FileDemo09 {
    public static void main(String[] args) {
        String dir = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes";
        File f = new File(dir);

        if (f.isDirectory()) {
            System.out.println(f.getPath() + " 路径是目录");
        } else {
            System.out.println(f.getPath() + " 路径不是目录");
        }
    }
}
