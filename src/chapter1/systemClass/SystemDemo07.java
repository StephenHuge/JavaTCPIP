package chapter1.systemClass;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 为System.err输出重定向。由于err打印错误信息，所以一般不要设置重定向。
 */
public class SystemDemo07 {
    public static void main(String[] args) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setErr(new PrintStream(out));
        System.err.println("I'm a err msg.");   // 输出到内存输出流中
        System.out.println("I'm a msg");        // 不会输出到内存输出流中，而是正常打印到打印台
        System.out.println("----------------------");
        System.out.println(out);                // 打印流中数据

    }
}
