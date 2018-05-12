package chapter1.systemClass;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用System类的输出流
 */
public class SystemDemo01 {
    public static void main(String[] args) {
        OutputStream out = System.out;  // 此时的输出流是向屏幕上输出
        try {
            out.write("I'm system's writer".getBytes());    // 向屏幕输出
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
