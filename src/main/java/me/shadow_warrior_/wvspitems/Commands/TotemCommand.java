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

public class TotemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack totem = new ItemStack(Material.DIAMOND_SPADE);
        ItemMeta itemMeta = totem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Kostur Szamana");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Kostur Szmana pozwoli ci stworzyc totemy" );
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "[PRAWY]");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Tworzy Totem Uzdrowienia ktory leczy  wojownikow");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "oraz usuwa im negatywne efekty w zasiegu");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "15 kratek znika po 20 sec");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "[LEWY]");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Tworzy Totem Zgubienia ktory przyciaga");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "potwory w zasiegu 15 kratek zadajac");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "im obrazenia znika po 20 sec");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "[SHIFT]");
        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Wystrzeliwuje szamana w kierunku totemu");
        itemMeta.setLore(lore);
        totem.setItemMeta(itemMeta);
        player.getInventory().addItem(totem);
        return true;
    }
}
