package org.ultimatejudge.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@SpringBootTest
class ServiceApplicationTests {

    @Test
    void test() {
        String serverAddress = "127.0.0.1"; // 服务器地址
        int serverPort = 9125; // 服务器端口
        byte[] buffer = new byte[100];
        try {
            Socket client = new Socket(serverAddress, serverPort);

            OutputStream outputStream = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);

            InputStream inputStream = client.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);
            int len = in.read(buffer);
            System.out.print(new String(buffer, 0, len));
            System.out.println();

            out.writeBytes("ping 123456");
//            len = in.read(buffer);
//            System.out.print(new String(buffer, 0, len));
//            System.out.println();

            client.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
