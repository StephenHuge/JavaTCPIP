package chapter1.file;

import java.io.File;

/**
 * 读取文件夹中的内容
 * 分别打印：
 * 1. 当前目录下的文件名称
 * 2. 给定目录下文件的完整路径名称
 */
public class FileDemo08 {
    public static void main(String[] args) {
        String dir = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes";
        File f = new File(dir);

        String[] str = f.list();        // 列出指定目录中的文件名称
        File[] files = f.listFiles();   // 列出目录中文件的完整路径，此时列表中的元素是File类型

        for (String s : str) {
            System.out.println(s);
        }
        System.out.println("--------------------");
        for (File file : files) {
            System.out.println(file.toString());
        }
    }
}
