package chapter1.systemClass;

/**
 * 使用err打印
 *  System.err.println();
 *
 * 和out的区别：打印错误尽量用前者，而正常打印用后者。二者在输出效果上无明显差别。
 */
public class SystemDemo02 {
    public static void main(String[] args) {
        String s = "I'm a error message";
        try {
            System.out.println(Integer.parseInt(s));
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
