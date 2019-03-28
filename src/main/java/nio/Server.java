package nio;

import java.util.TreeMap;

public class Server {
    public static void main(String[] args){
        int port = 8088;
        MultipleServer server = new MultipleServer(port);

        new Thread(server,"NIO-MultipleServer-001").start();
    }
}
