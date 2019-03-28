package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class MultipleServer implements Runnable{
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    public MultipleServer(int port){
        try {
            //获得一个选择器
            selector = Selector.open();
            //获得一个通路
            serverSocketChannel = ServerSocketChannel.open();
            //设置通路为非阻塞
            serverSocketChannel.configureBlocking(false);
            //将通道绑定到端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            //将通道注册到选择器 监听ACCEPT事件 当事件发生会调用selector的select()方法
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server is start at port: " + port);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() !=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(selector != null){
                    try {
                        selector.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            if(key.isAcceptable()){
                //得到客户端套接字的通道
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                //设置客户端通道为非阻塞
                sc.configureBlocking(false);
                //将新接入的客户端连接注册到选择器，监听读操作
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                //读取客户端的消息
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                //处理客户端的消息
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("Server received: " + body);
                    //给客户端返回响应数据
                    doWrite(sc);
                }else if(readBytes < 0){
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel sc) throws IOException{
        String response = "Deal successfully: " + System.currentTimeMillis();
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
    }
}
