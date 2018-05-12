package chapter1.printStream;

import chapter1.constant.IOConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 打印流的格式化输出。
 */
public class PrintDemo02 {
    public static void main(String[] args) throws Exception {
        File f = new File(IOConstants.PATH_TEST_TXT);

        // 通过FileOutputStream实例化，意味着所有的输出是向文件中打印
        PrintStream ps = new PrintStream(new FileOutputStream(f));

        String name = "Steve";
        int age = 24;
        float score = 99.2f;
        char sex = 'M';
        // 格式化输出，字符串使用%s、整数使用%d、小数使用%f、字符使用%c
        ps.printf("姓名：%s\n年龄：%d\n成绩：%.2f\n性别：%c"
                , name, age, score, sex);
        ps.close();
    }
}
