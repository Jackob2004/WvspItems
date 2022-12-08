package me.shadow_warrior_.wvspitems.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class AtomowkaListener implements Listener {
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getPlayer().getItemInHand().getItemMeta() == null || e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null || (e.getPlayer().getItemInHand() == null)) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "ATOMOWKA")){
            player.playSound(player.getLocation(), Sound.COW_HURT,1,1);
            e.getPlayer().getWorld().createExplosion(player.getLocation(),1);
            double x = 5;
            double y = 5;
            double z = 5; //5,10,15,20
            int a = 25; // 25,15,10,5
            for (int i = 0;i<=3;i++) {
                for (Entity entity : player.getNearbyEntities(x, y, z)) {
                    if (entity.getType().equals(EntityType.PLAYER)) {
                        Player p = (Player) entity;
                        p.setHealth(p.getHealth() - a);
                    }
                }
                x = x+5;
                y = y+5;
                z = z+5;
                a = a-5;
            }
            player.setHealth(0);
        }
    }

}
