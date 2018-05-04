package cn.mrcode.newstudy.hpbase._09.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/05/04     zhuqiang        -
 * </pre>
 * @author zhuqiang
 * @version 1.0.0 2018/5/4 17:46
 * @date 2018/5/4 17:46
 * @since 1.0.0
 */
public class HttpServer {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        new HttpServer().start(8096);
    }

    public void start(int port) throws IOException {
        ServerSocket ss = new ServerSocket(port);

        while (true) {
            try {
                Socket socket = ss.accept();
                executorService.execute(new RequestWork(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
