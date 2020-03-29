package com.github.skpersonal.remoteaccesss;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ReceiveData extends Thread {

    private boolean isActive = true;

    void stopThread() {
        this.isActive = false;
    }

    @Override
    public void run() {
        while (this.isActive) {
            try {
                ServerSocket svsocket = new ServerSocket(25585);
                Socket socket = svsocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true);
                String str;
                while (true) {
                    str = reader.readLine();
                    if (str.equalsIgnoreCase("q")) {
                        break;
                    } else {
                        System.out.println(str);
                        //Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
                    }
                }
                reader.close();
                writer.close();
                input.close();
                output.close();
                socket.close();
                svsocket.close();
            } catch (SocketException se) {
                System.out.println("socketが切断されました");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
