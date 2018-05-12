package chapter1.byteArrayStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;

/**
 * 内存操作流ByteArrayInputStream 和 ByteOutputStream
 * 其实就是将内容放入一个输出流对象中，需要用的时候调取相关API
 *
 */
public class ByteArrayDemo01 {
    public static void main(String[] args) {
        String str = "HELLOWOLRD";   // 全部是大写

        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        ByteOutputStream out = new ByteOutputStream();

        int temp = 0;

        while ((temp = in.read()) != -1) {
            char c = (char) temp;
            out.write(Character.toLowerCase(c));    // 变换成小写写到输出流中
        }
        String newString = out.toString();
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(newString);
    }
}
