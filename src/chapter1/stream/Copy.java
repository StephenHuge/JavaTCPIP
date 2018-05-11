package chapter1.stream;

import java.io.*;

/**
 * 完成copy功能：
 * 输入 java Copy sourceFile destinationFile
 *
 * Program arguments : /home/hjs/Codes/test.txt /home/hjs/Codes/test3.txt
 */
public class Copy {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("输入参数失误！");
            System.out.println("格式：java Copy 源文件 目标文件");
            System.exit(1);
        }
        File source = new File(args[0]);
        File destination = new File(args[1]);

        if (!source.exists()) {
            System.out.println("源文件不存在！");
            System.exit(1);
        }
        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(destination);

        if ((in != null) || (out != null)) {
            int data;
            try {
                while ((data = in.read()) != -1) {
                    out.write(data);
                }
                System.out.println("复制完成");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("复制失败");
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
