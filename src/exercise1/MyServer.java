package exercise1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by adrian on 14.02.2015.
 */
public class MyServer {
    public static void start(int port){
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port); Socket cn = serverSocket.accept(); BufferedReader bis = new BufferedReader(new InputStreamReader(cn.getInputStream())); BufferedOutputStream bos = new BufferedOutputStream(cn.getOutputStream())) {
                String line = bis.readLine();
                String page = line.split(" ")[1].replace("/", "");
                System.out.println(page);
                try (FileInputStream fs = new FileInputStream("htdocs/" + page)) {
                    int readByte;
                    while ((readByte = fs.read()) != -1) {
                        bos.write((byte) readByte);
                    }
                }
//            bos.write("Hello from MyServer".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
