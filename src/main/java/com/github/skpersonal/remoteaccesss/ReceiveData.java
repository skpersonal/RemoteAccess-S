package com.github.skpersonal.remoteaccesss;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ReceiveData implements Runnable {

    private boolean isActive = true;

    void stopThread() {
        isActive = false;
    }

    @Override
    public void run() {
        try {
            ServerSocket svsocket = new ServerSocket(25585);
            while (isActive) {
                Socket socket = svsocket.accept();
                Connection connection = new Connection(socket);
                Thread thread = new Thread(connection);
                thread.start();
            }
        } catch (BindException e) {

        } catch (SocketException se) {
            System.out.println("socketが切断されました");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
