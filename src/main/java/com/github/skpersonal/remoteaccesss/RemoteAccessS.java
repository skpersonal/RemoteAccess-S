package com.github.skpersonal.remoteaccesss;

import org.bukkit.plugin.java.JavaPlugin;

public final class RemoteAccessS extends JavaPlugin {
    private ReceiveData receiveData;

    @Override
    public void onEnable() {
        receiveData = new ReceiveData();
        receiveData.start();
    }

    @Override
    public void onDisable() {
        receiveData.stopThread();
    }
}
