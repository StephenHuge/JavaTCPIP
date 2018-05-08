package chapter1.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 列出指定目录下所有文件
 */
public class FileDemo11 {
    public static void main(String[] args) {
        String dir = File.separator + "home"
                + File.separator + "hjs";
//                + File.separator + "Codes"
//                + File.separator + "TestDir";
//                + File.separator + "ZipsForAlgorithms";
        File f = new File(dir);
        print(f);
    }
    public static void print(File file) {
        if (file == null) return;

        Content root = new Content(null, file, 0, 1);   // 根结点
        ArrayList<Integer> fileNumOfEachLayer = new ArrayList<>();          // 记录每层文件的数量
        ArrayList<Integer> fnel = fileNumOfEachLayer;   // 名称缩写
        int layer = 0;                  // 当前所在的层数

        fnel.add(layer, 1);     // 将当前层数文件的数量记录在list中

        File thisFile = root.getFile();          // 获取当前节点的文件
        System.out.println(thisFile.getPath());  // 打印当前节点的文件

        String[] sons = thisFile.list();   // 获取当前节点文件的子文件数组

        if (sons != null) {
            fnel.add(layer + 1, sons.length);   // 将下一层文件的数量记录在list中

            // 循环下一层所有文件
            for (int i = 0; i < sons.length; i++) {
                File f = new File(thisFile, sons[i]);
                Content c = new Content(root, f, layer + 1, i);
                // 递归执行
                print(c, fnel);
            }
        }
    }
    private static void print(Content c, ArrayList<Integer> fnel) {
        if (c == null || fnel == null)  return;
        File f = c.getFile();       // 获取当前节点文件
        String[] sons = f.list();   // 获取当前节点文件的子文件数组
        // 判断如何打印并打印当前行
//        System.out.print(
//                String.format("(%d, %d)",
//                        c.getLayer(), c.getId())
//        );

        print(c, fnel, c.getLayer());
        if (c.getFile().list() != null) {
            System.out.print("+ ");
        } else {
            System.out.print("- ");
        }
        String[] ss = c.getFile().getPath().split("/");
        System.out.println(ss[ss.length - 1]);

        // 如果此文件为文件夹，继续打印下一层
        if (sons != null) {
            fnel.add(c.getLayer() + 1, sons.length);
            for (int i = 0; i < sons.length; i++) {
                File sonFile = new File(f, sons[i]);
                Content sonContent = new Content(c, sonFile, c.getLayer() + 1, i);
                // 递归执行
                print(sonContent, fnel);
            }
        }
    }
    private static void print(Content c, ArrayList<Integer> fnel, int non) {
        // 递归直到root下一层
        if (c.getParent() != null) {
            print(c.getParent(), fnel, non);
        }

        if (c.getLayer() == non)    return;
        // 打印当前行
//        System.out.print(String.format("%d ~ %d", fnel.get(c.getLayer()) - 1, c.getId()));
//        System.out.print(String.format("%d ~ %d", fnel.get(c.getLayer()) - 1, c.getLayer()));
        if (fnel.get(c.getLayer()) - 1 > c.getId() ) {   // 判断当前文件是否是自己所在层的最后一个文件
            System.out.print("│ ");
        } else {
            System.out.print("  ");
        }
    }
static class Content {
    private Content parent;
    private File file;
    private int layer;      // 相对于根目录此文件的层数
    private int id;   // 本层中所有的文件数，此变量是为了判定最后一个文件用的

    public Content(Content parent, File file, int layer, int id) {
        this.parent = parent;
        this.file = file;
        this.layer = layer;
        this.id = id;
    }

    public Content getParent() {
        return parent;
    }

    public void setParent(Content parent) {
        this.parent = parent;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
    /*public static void print(File file, int layer, boolean flag) {
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
    }*/
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
