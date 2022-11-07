package me.shadow_warrior_.wvspitems.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AtomowkaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack atomowka = new ItemStack(Material.EXPLOSIVE_MINECART);
        ItemMeta itemMeta = atomowka.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "ATOMOWKA");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "[PRAWY]");
        lore.add(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kliknij, aby wywolac potezna eksplozje" );
        itemMeta.setLore(lore);
        atomowka.setItemMeta(itemMeta);
        player.getInventory().addItem(atomowka);
        return true;
    }
}
