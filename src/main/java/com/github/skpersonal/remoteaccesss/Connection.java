package com.github.skpersonal.remoteaccesss;

import org.bukkit.Bukkit;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Connection implements Runnable {
    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            PrintWriter writer = new PrintWriter(output, true);
            ConsoleHandler handler = new ConsoleHandler(writer);
            ChatHandler chatHandler = new ChatHandler(writer);
            Bukkit.getServer().getLogger().addHandler(handler);
            Bukkit.getServer().getPluginManager().registerEvents(chatHandler, RemoteAccessS.getProvidingPlugin(RemoteAccessS.class));
            String str;
            while (true) {
                str = reader.readLine();
                if (str.equalsIgnoreCase("q")) {
                    System.out.println("Connection finished");
                    break;
                } else {
                    System.out.println(str);
                    //Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
                }
            }
            chatHandler.close();
            Bukkit.getServer().getLogger().removeHandler(handler);
            reader.close();
            writer.close();
            input.close();
            output.close();
        } catch (SocketException se) {
            System.out.println("Connection lost");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
