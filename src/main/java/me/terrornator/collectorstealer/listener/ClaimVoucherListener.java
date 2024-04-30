package me.terrornator.collectorstealer.listener;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import de.tr7zw.kore.nbtapi.NBTItem;
import me.terrornator.collectorstealer.CollectorStealer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClaimVoucherListener implements Listener {

    Configuration config = CollectorStealer.getInstance().getConfig();


    @EventHandler
    public void onClaim(PlayerInteractEvent event){
        Player player = event.getPlayer();
        FPlayer factionsPlayer = FPlayers.getInstance().getByPlayer(player);
        if (event.getItem() == null){
            return;
        }
        if (!event.getItem().getType().equals(Material.valueOf(config.getString("voucher.material")))){
            return;
        }
        NBTItem nbtItem = new NBTItem(event.getItem());
        if (!nbtItem.hasNBTData()){
            return;
        }
        if (!nbtItem.getString("voucher").equals("collector")){
            return;
        }
        ConsoleCommandSender console = CollectorStealer.getInstance().getServer().getConsoleSender();
        if (!nbtItem.getInteger("money").equals(0)||!nbtItem.getInteger("tnt").equals(0)) {
            Bukkit.dispatchCommand(console, "eco give " + player.getName() + " " + nbtItem.getInteger("money"));
            Bukkit.dispatchCommand(console, "f tnt give " +nbtItem.getInteger("tnt") + " " + factionsPlayer.getFaction().getTag());
            player.getInventory().remove(nbtItem.getItem());
        }
    }
}
