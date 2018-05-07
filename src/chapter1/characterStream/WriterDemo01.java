package chapter1.characterStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 字符流Writer基本用法，直接把内容写出到文件
 *  Writer writer = new FileWriter(f);
 *  String str = "Hello Shit!";
 *  writer.write(str);
 *
 */
public class WriterDemo01 {
    public static void main(String[] args) throws IOException {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);

        Writer writer = new FileWriter(f);
        String str = "Hello Shit!";

        writer.write(str);
        writer.close();
    }
}
