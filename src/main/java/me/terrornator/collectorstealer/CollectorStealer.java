package me.terrornator.collectorstealer;

import lombok.Getter;
import me.terrornator.collectorstealer.listener.ClaimVoucherListener;
import me.terrornator.collectorstealer.listener.CollectorExplodeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CollectorStealer extends JavaPlugin {
    @Getter
    public static CollectorStealer instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.registerListeners();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new CollectorExplodeListener(), this);
        getServer().getPluginManager().registerEvents(new ClaimVoucherListener(), this);
    }
}
