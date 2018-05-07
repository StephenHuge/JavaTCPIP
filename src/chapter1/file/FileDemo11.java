package chapter1.file;

import java.io.File;
import java.io.IOException;

/**
 * 列出指定目录下所有文件
 */
public class FileDemo11 {
    public static void main(String[] args) {
        String dir = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "TestDir";
//                + File.separator + "ZipsForAlgorithms";
        File f = new File(dir);
        print(f, 0, true);
    }
    public static void print(File file, int layer, boolean flag) {
        if (file != null) {
            layer++;
            String[] strings = file.list();
            if (strings != null) {

                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].startsWith(".")) continue;
                    if (strings[i].endsWith(".class")) continue;
                    if (strings[i].equals("EclipseWorkSpace")
                            || strings[i].equals("ZipsForAlgorithms")
                            || strings[i].equals("IDEAWorkSpace")) continue;
                    int a = layer;
                    if (i != strings.length - 1) flag = true;
//                        if (a != 1) System.out.print("│ ");
                    while (a != 0) {
                        if (a != layer && flag) {
                            System.out.print("│ ");
                        }
                        else {
                            System.out.print("  ");
                        }
                        a--;
                    }
                    System.out.print("- ");
                    System.out.print(strings[i]);
                    System.out.println(
                            String.format(" - (layer : %d, length : %d, i : %d, flag : %b)",
                                    layer, strings.length, i, flag)
                    );
                    File f = new File(file, strings[i]);
                    if (i == strings.length - 1) flag = false;
                    print(f, layer, flag);
                }
            }
        }
    }
}
 /*layer = layer << 1 | 0x1;
            System.out.println(Integer.toBinaryString(layer));
            String[] strings = file.list();
            int l = layer;  // 临时变量
            for (int i = 0; i < strings.length; i++) {
                int m = Byte.SIZE;
                while (m-- > 0) {
                    if ((l & 0x80) == 0x80) {
                        System.out.print("│ ");
                    } else {
                        System.out.print("  ");
                    }
                    l <<= 1;
                }
                System.out.print("- ");
                System.out.println(strings[i]);

                File f = new File(file, strings[i]);
                print(f, layer);
            }*/
/*  */
