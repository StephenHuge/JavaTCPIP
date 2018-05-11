package chapter1.stream;

import static chapter1.constant.IOConstants.*;

import java.io.*;

/**
 * 字符流Writer和字节流OutputStream对比
 * 1. 是否有缓冲
 *  结果：同时不关闭，发现Writer只是创建文件而未写入，
 *       因为它使用了buffer，只有手动使用flush()方法后，才会正常写入。
 * 2. 使用哪个？
 *  使用字节流更好，因为在硬盘中文件储存形式都是字节，而且在硬盘或者内存中
 *  传输方式都是字节，例如图片等都是按字节存储。字符只会在内存中形成。
 *  所以字节流使用更加广泛。
 */
public class OutputStreamDemo03 {
    public static void main(String[] args) throws Exception {
        String str = "HelloWorld!";

        File f1 = new File(PATH_CODES + "/test1.txt");
        File f2 = new File(PATH_CODES + "/test2.txt");
        OutputStream out = new FileOutputStream(f1);
        Writer writer = new FileWriter(f2);

        byte[] bytes = str.getBytes();

        out.write(bytes);
        writer.write(str);

        writer.flush();
        // 同时不关闭
//        out.close();
//        writer.close();
    }
}
