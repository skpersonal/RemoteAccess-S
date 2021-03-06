package com.github.skpersonal.remoteaccesss;

import org.bukkit.plugin.java.JavaPlugin;

public final class RemoteAccessS extends JavaPlugin {
    private ReceiveData receiveData;

    @Override
    public void onEnable() {
        receiveData = new ReceiveData();
        Thread thread = new Thread(receiveData);
        thread.start();
    }

    @Override
    public void onDisable() {
        receiveData.stopThread();
    }
}
