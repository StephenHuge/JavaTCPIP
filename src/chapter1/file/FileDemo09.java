package chapter1.file;

import java.io.File;

/**
 * �ж�·���Ƿ�ΪĿ¼
 */
public class FileDemo09 {
    public static void main(String[] args) {
        String dir = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes";
        File f = new File(dir);

        if (f.isDirectory()) {
            System.out.println(f.getPath() + " ·����Ŀ¼");
        } else {
            System.out.println(f.getPath() + " ·������Ŀ¼");
        }
    }
}
