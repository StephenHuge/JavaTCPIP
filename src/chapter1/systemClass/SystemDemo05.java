package chapter1.systemClass;

import chapter1.constant.IOConstants;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 输出重定向：setOut， setErr， setId。
 * 将原有输出类型换成新的输出
 */
public class SystemDemo05 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(
                IOConstants.PATH_TEST_TXT
        )));
        System.out.println("I'm a Sout -> fileOutputStream");
        System.out.println("GOOD LUCK");
    }
}
