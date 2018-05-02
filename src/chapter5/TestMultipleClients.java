package chapter5;

public class TestMultipleClients {
    public static void main(String[] args) throws Exception {
        int num = 10;
        String server = "127.0.0.1";
        String wordPrefix = "shit - ";
        String port = "9901";

        String[] params = new String[] {server, wordPrefix, port};

        for (int i = 0; i < num; i++) {
            params[1] = params[1].substring(0, 7) + i;
            TCPEchoClientNonblocking.main(params);
        }
    }
}
