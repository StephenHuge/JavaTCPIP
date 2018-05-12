package chapter1.systemClass;

import chapter1.constant.IOConstants;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 如何使用重定向保存错误LOG。
 *
 */
public class SystemDemo06 {
    public static void main(String[] args) throws FileNotFoundException {
        String str = "This is an error log.";
        try {
            System.out.println(Integer.parseInt(str));  // 这句会抛出异常
        } catch (Exception e) {
            try {
                System.setOut(new PrintStream(new FileOutputStream(
                        IOConstants.PATH_ERR_LOG
                )));            // 输出重定位
            } catch (Exception e1) {
                e.printStackTrace();
            }
            SimpleDateFormat bartDateFormat = new SimpleDateFormat
                    ("EEEE-MMMM-dd-yyyy");
            Date date = new Date();
            System.out.print(bartDateFormat.format(date) + " ");    // 保存日期
            System.out.println(e);  // 输出错误，保存到文件中

        }
    }
}
