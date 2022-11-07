package me.shadow_warrior_.wvspitems.Listeners;

import me.shadow_warrior_.wvspitems.Holders.Cooldowns;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GandalfStaff extends Cooldowns implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getPlayer().getItemInHand().getType().equals(Material.GOLD_HOE) && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (!getCooldown4().containsKey(player.getUniqueId()) || System.currentTimeMillis() - getCooldown4().get(player.getUniqueId()) >= 50000) {
                getCooldown4().put(player.getUniqueId(), System.currentTimeMillis());
                for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
                    if (entity.getType().equals(EntityType.PLAYER)) {
                        Player p = (Player) entity;
                        if (player.getHealth() <= 20 && player.getHealth() > 10) staff(player,p,60,4,-1.6,1.2);

                        else if (player.getHealth() <= 10 && player.getHealth() > 6) staff(player,p,80,8,-2.6,1.4);

                        else if (player.getHealth() <= 6 && player.getHealth() > 0) staff(player,p,100,12,-3.6,1.6);


                    }
                }
            } else
                player.sendMessage("Musisz poczekac: " + ((50000 - (System.currentTimeMillis() - getCooldown4().get(player.getUniqueId()))) / 1000) + " sekund, aby uzyc swojej umiejetnosci ponownie.");
        }
    }
    public void staff(Player player,Player p, Integer duration,Integer health,Double multiply, Double y){
        player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
        if(p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            p.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            p.setVelocity(p.getVelocity().clone().add(player.getLocation().clone().toVector().subtract(p.getLocation().clone().toVector()).normalize().multiply(multiply).setY(y)));
            p.setHealth(p.getHealth() - health);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 1));
        }
    }
}
