package me.shadow_warrior_.wvspitems.Listeners;

import me.shadow_warrior_.wvspitems.Holders.Cooldowns;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BuffaloHorn extends Cooldowns implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player ppp = e.getPlayer();
        if (e.getPlayer().getItemInHand().getType().equals(Material.CARROT_STICK) && e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getPlayer().getItemInHand().getType().equals(Material.CARROT_STICK) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(!getCooldown5().containsKey(ppp.getUniqueId()) || System.currentTimeMillis() - getCooldown5().get(ppp.getUniqueId()) >= 100000){
                getCooldown5().put(ppp.getUniqueId(), System.currentTimeMillis());
                if(ppp.hasPotionEffect(PotionEffectType.WATER_BREATHING)){
                    ppp.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,800,3));
                    ppp.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,60,2));
                    ppp.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,300,2));
                    ppp.playSound(ppp.getEyeLocation(),"buffaloHorn",1,1);
                    Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "! " +ChatColor.AQUA + ppp.getDisplayName() + ChatColor.YELLOW +" Uzyl " + ChatColor.AQUA + "Rogu Wojennego!");
                }
                for (Entity entity : ppp.getNearbyEntities(40, 40, 40)) {
                    if (entity.getType().equals(EntityType.PLAYER)) {
                        Player player = (Player) entity;
                        if (player.hasPotionEffect(PotionEffectType.WATER_BREATHING))  player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
                    }
                }
            }else ppp.sendMessage("Musisz poczekac: " + ((100000 - (System.currentTimeMillis() - getCooldown5().get(ppp.getUniqueId())))/1000) + " sekund, aby uzyc rogu.");
        } else if (e.getPlayer().getItemInHand().getType().equals(Material.CARROT_STICK) && e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getPlayer().getItemInHand().getType().equals(Material.CARROT_STICK) && e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            if(((100000 - (System.currentTimeMillis() - getCooldown5().get(ppp.getUniqueId())))/1000) >=0) ppp.sendMessage("Musisz poczekac: " + ((100000 - (System.currentTimeMillis() - getCooldown5().get(ppp.getUniqueId())))/1000) + " sekund, aby uzyc rogu.");
            else ppp.sendMessage(ChatColor.GREEN + "Mozesz uzyc rogu");
        }
    }

}
