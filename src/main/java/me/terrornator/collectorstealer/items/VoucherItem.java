package me.terrornator.collectorstealer.items;

import lombok.Getter;
import me.terrornator.collectorstealer.CollectorStealer;
import me.terrornator.collectorstealer.util.ItemBuilder;
import me.terrornator.collectorstealer.util.StringUtil;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;

public class VoucherItem {
    @Getter
    public VoucherItem voucher;
    Configuration config = CollectorStealer.getInstance().getConfig();

    public ItemStack createVoucher(String money, String tnt){
        ItemStack item = new ItemBuilder(Material.valueOf(config.getString("voucher.material")))
                .setName(StringUtil.format(config.getString("voucher.name")))
                .setLore(StringUtil.format(config.getStringList("voucher.lore"), "%money%", money ,"%tnt%", tnt))
                .setNBTString("voucher", "collector")
                .setNBTInteger("money", Integer.valueOf(money))
                .setNBTInteger("tnt", Integer.valueOf(tnt))
                .build();
        return item;
    }
}
