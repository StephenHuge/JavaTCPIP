package chapter1.randomAccessFile;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * �����д������ʵ�ֶ��ļ����ݵĶ�ȡ������ȴ���ڸ��ӣ�һ��ʹ���ֽڻ����ַ�����
 */
public class RandomAccessFileDemo02 {

    public static final String RW = "rw";
    public static final String R = "r";

    public static void main(String[] args) throws Exception {
        String path = File.separator + "home"
                + File.separator + "hjs"
                + File.separator + "Codes"
                + File.separator + "test.txt";
        File f = new File(path);
        RandomAccessFile rdf = null;
        rdf = new RandomAccessFile(f, R);

        String name = null;
        int age = 0;
        byte[] bytes = new byte[8]; // ׼���ռ��ȡ����
        rdf.skipBytes(12);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = rdf.readByte();  // ѭ������ǰ8������
        }
        name = new String(bytes);
        age = rdf.readInt();

        System.out.println(String.format("��һ���ˣ�%s - %d��", name, age));
        rdf.seek(0);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = rdf.readByte();  // ѭ������ǰ8������
        }
        name = new String(bytes);
        age = rdf.readInt();

        System.out.println(String.format("�ڶ����ˣ�%s - %d��", name, age));
        rdf.skipBytes(12);

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = rdf.readByte();  // ѭ������ǰ8������
        }
        name = new String(bytes);
        age = rdf.readInt();

        System.out.println(String.format("�������ˣ�%s - %d��", name, age));
        rdf.close();
    }
}
