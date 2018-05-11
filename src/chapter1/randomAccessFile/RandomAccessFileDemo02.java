package chapter1.randomAccessFile;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 随机读写流可以实现对文件内容的读取，但是却过于复杂，一般使用字节或者字符流。
 */
public class RandomAccessFileDemo02 {

    public static final String RW = "rw";
    public static final String R = "r";

    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        RandomAccessFile rdf = null;
        rdf = new RandomAccessFile(f, R);

        String name = null;
        int age = 0;
        byte[] bytes = new byte[8]; // 准备空间读取姓名
        rdf.skipBytes(12);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = rdf.readByte();  // 循环读出前8个内容
        }
        name = new String(bytes);
        age = rdf.readInt();

        System.out.println(String.format("第一个人：%s - %d岁", name, age));
        rdf.seek(0);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = rdf.readByte();  // 循环读出前8个内容
        }
        name = new String(bytes);
        age = rdf.readInt();

        System.out.println(String.format("第二个人：%s - %d岁", name, age));
        rdf.skipBytes(12);

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = rdf.readByte();  // 循环读出前8个内容
        }
        name = new String(bytes);
        age = rdf.readInt();

        System.out.println(String.format("第三个人：%s - %d岁", name, age));
        rdf.close();
    }
}
