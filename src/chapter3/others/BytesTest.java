package chapter3.others;

import java.awt.*;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/12/30.
 */
public class BytesTest {
    public static void printByteArray(byte[] arr) {
        for (byte b : arr)
            System.out.print(b + "\t");
        System.out.println();
    }
    public static void main(String[] args) {
        try {
            byte[] a1 = "Test!".getBytes();
            byte[] a2 = "Test!".getBytes("UTF-16BE");
            printByteArray(a1);
            printByteArray(a2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
