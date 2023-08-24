package org.ultimatejudge.core.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeExecutor {
    private CodeExecutorStatus status;
    private String serverAddress;
    private Integer serverPort;
    private Socket socket;
    private OutputStream outputStream;
    private PrintWriter out;

    public void connectToCodeExecutorServer() {
        try {
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            out = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String judgeCode(String code) {
        connectToCodeExecutorServer();
        String response = pingServer();
        System.out.println("Connect to server, response: " + response);
        out.print(code);
        return null;
    }

    public String pingServer() {
        String message = "ping";
        out.print(message);
        return "";
    }
}
