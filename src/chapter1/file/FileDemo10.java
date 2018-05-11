package chapter1.file;

import java.io.File;
import java.io.IOException;

/**
 * �г�ָ��Ŀ¼�������ļ�
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
                    if (f != null)  print(f);   // �ݹ����print()������ӡ
                }
            } else {
                System.out.println(" " + file);
            }
        }
    }
}
