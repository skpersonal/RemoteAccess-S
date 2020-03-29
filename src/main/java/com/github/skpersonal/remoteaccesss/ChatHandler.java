package com.github.skpersonal.remoteaccesss;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.PrintWriter;

public class ChatHandler implements Listener {
    private PrintWriter writer;
    private boolean isActive = true;

    ChatHandler(PrintWriter writer) {
        this.writer = writer;
    }

    void close() {
        isActive = false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (isActive) {
            writer.println("<" + e.getPlayer().getName() + "> " + e.getMessage());
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent e) {
        if (isActive) {
            writer.println(e.getSender().getName() + " issued server command: " + e.getCommand());
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (isActive) {
            writer.println(e.getPlayer().getName() + " issued server command: " + e.getMessage());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (isActive) {
            writer.println("UUID of player " + e.getPlayer().getName() + " is " + e.getPlayer().getUniqueId().toString());
        }
    }
}
