package chapter1.characterStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 字符流Writer基本用法，直接把内容写出到文件，区别是可以在文件中追加内容
 *  Writer writer = new FileWriter(f, true);    // 和Stream类似，可以追加内容
 *
 */
public class WriterDemo02 {
    public static void main(String[] args) throws IOException {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);

        Writer writer = new FileWriter(f, true);    // 和Stream类似，可以追加内容
        String str = "Hello Shit!";

        writer.write(str);

        writer.close();
    }
}
