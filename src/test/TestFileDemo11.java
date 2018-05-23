package test;

import chapter1.file.FileDemo11;

import java.io.File;

public class TestFileDemo11 {
    public static void main(String[] args) {
        String dir = "D:\\MYCODE\\MYSOURCECODE";
//		String[] ignoredFolders = {"bin", "lib", ".gradle", "example", "webapps", "work"};
//		String[] ignoredSuffixes = {"log", "html", "txt", "jsp", "class", "java"};
        String[] ignoredFolders = {"bin", "lib", ".gradle", "example", "webapps", "work"};
        String[] ignoredSuffixes = {};
        File f = new File(dir);
        FileDemo11.print(f, 2/*, ignoredFolders, ignoredSuffixes*/);
    }
}
