package chapter1.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * �г�ָ��Ŀ¼�������ļ�
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

        Content root = new Content(null, file, 0, 1);   // �����
        ArrayList<Integer> fileNumOfEachLayer = new ArrayList<>();          // ��¼ÿ���ļ�������
        ArrayList<Integer> fnel = fileNumOfEachLayer;   // ������д
        int layer = 0;                  // ��ǰ���ڵĲ���

        fnel.add(layer, 1);     // ����ǰ�����ļ���������¼��list��

        File thisFile = root.getFile();          // ��ȡ��ǰ�ڵ���ļ�
        System.out.println(thisFile.getPath());  // ��ӡ��ǰ�ڵ���ļ�

        String[] sons = thisFile.list();   // ��ȡ��ǰ�ڵ��ļ������ļ�����

        if (sons != null) {
            fnel.add(layer + 1, sons.length);   // ����һ���ļ���������¼��list��

            // ѭ����һ�������ļ�
            for (int i = 0; i < sons.length; i++) {
                File f = new File(thisFile, sons[i]);
                Content c = new Content(root, f, layer + 1, i);
                // �ݹ�ִ��
                print(c, fnel);
            }
        }
    }
    private static void print(Content c, ArrayList<Integer> fnel) {
        if (c == null || fnel == null)  return;
        File f = c.getFile();       // ��ȡ��ǰ�ڵ��ļ�
        String[] sons = f.list();   // ��ȡ��ǰ�ڵ��ļ������ļ�����
        // �ж���δ�ӡ����ӡ��ǰ��
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

        // ������ļ�Ϊ�ļ��У�������ӡ��һ��
        if (sons != null) {
            fnel.add(c.getLayer() + 1, sons.length);
            for (int i = 0; i < sons.length; i++) {
                File sonFile = new File(f, sons[i]);
                Content sonContent = new Content(c, sonFile, c.getLayer() + 1, i);
                // �ݹ�ִ��
                print(sonContent, fnel);
            }
        }
    }
    private static void print(Content c, ArrayList<Integer> fnel, int non) {
        // �ݹ�ֱ��root��һ��
        if (c.getParent() != null) {
            print(c.getParent(), fnel, non);
        }

        if (c.getLayer() == non)    return;
        // ��ӡ��ǰ��
//        System.out.print(String.format("%d ~ %d", fnel.get(c.getLayer()) - 1, c.getId()));
//        System.out.print(String.format("%d ~ %d", fnel.get(c.getLayer()) - 1, c.getLayer()));
        if (fnel.get(c.getLayer()) - 1 > c.getId() ) {   // �жϵ�ǰ�ļ��Ƿ����Լ����ڲ�����һ���ļ�
            System.out.print("�� ");
        } else {
            System.out.print("  ");
        }
    }
static class Content {
    private Content parent;
    private File file;
    private int layer;      // ����ڸ�Ŀ¼���ļ��Ĳ���
    private int id;   // ���������е��ļ������˱�����Ϊ���ж����һ���ļ��õ�

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
//                        if (a != 1) System.out.print("�� ");
                    while (a != 0) {
                        if (a != layer && flag) {
                            System.out.print("�� ");
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
            int l = layer;  // ��ʱ����
            for (int i = 0; i < strings.length; i++) {
                int m = Byte.SIZE;
                while (m-- > 0) {
                    if ((l & 0x80) == 0x80) {
                        System.out.print("�� ");
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
