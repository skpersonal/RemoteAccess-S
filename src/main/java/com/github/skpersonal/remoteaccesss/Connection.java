package com.github.skpersonal.remoteaccesss;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    static public final int BUFSIZE = 32;
    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
