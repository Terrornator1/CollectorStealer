package me.terrornator.collectorstealer.listener;

import com.golfing8.kore.FactionsKore;
import com.golfing8.kore.expansionstacker.features.CollectionBeaconFeature;
import com.golfing8.kore.object.CollectionBeacon;
import me.terrornator.collectorstealer.items.VoucherItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class CollectorExplodeListener implements Listener {
    CollectionBeaconFeature collector = FactionsKore.get().getFeature(CollectionBeaconFeature.class);

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onExplodeCollector(EntityExplodeEvent event){
        for (Block collectors: event.blockList()){
            if(!(collectors.getType().equals(collector.getBeaconItem().getType()))){
                continue;
            }
            CollectionBeacon explodedCollector = collector.getCollectionBeacon(collectors.getLocation());
            long sellAmount;
            if (explodedCollector.getPotentialSell() == null){
                sellAmount = 0;
            }
            else {
                sellAmount = Long.parseLong(explodedCollector.getPotentialSell().replace(",", ""));
            }
            for (Map.Entry<Material, Integer> materials: explodedCollector.getAmounts().entrySet()) {
                if (materials.getKey() == Material.TNT){
                    ItemStack voucher = new VoucherItem().createVoucher(String.valueOf(sellAmount), String.valueOf(materials.getValue()));
                    explodedCollector.getLocation().getWorld().dropItem(explodedCollector.getLocation(), voucher);
                }
            }
        }
    }
}
