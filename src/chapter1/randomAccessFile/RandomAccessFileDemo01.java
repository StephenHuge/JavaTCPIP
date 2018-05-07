package chapter1.randomAccessFile;

import java.io.File;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo01 {

    public static final String RW = "rw";

    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        RandomAccessFile rdf = null;
        rdf = new RandomAccessFile(f, RW);

        String name = null; int age = 0;
        name = "zhangsan";  age = 30;
        rdf.writeBytes(name);
        rdf.writeInt(age);

        // 默认name长度为8字节， age为4字节
        name = "Lisa    ";  age = 31;
        rdf.writeBytes(name);
        rdf.writeInt(age);

        name = "Sam     ";  age = 25;
        rdf.writeBytes(name);
        rdf.writeInt(age);

        rdf.close();
    }
}
