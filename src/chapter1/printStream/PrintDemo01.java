package chapter1.printStream;

import chapter1.constant.IOConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 打印流，和OutputStream直接输出相比，明显方便了很多。
 * 其中包装了OutputWriter。
 */
public class PrintDemo01 {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File(IOConstants.PATH_TEST_TXT);

        // 通过FileOutputStream实例化，意味着所有的输出是向文件中打印
        PrintStream ps = new PrintStream(new FileOutputStream(f));
        ps.print("I'm ");
        ps.println("a printWriter");
        ps.print("1 + 1 = 2");

        ps.close();
    }
}
