package me.shadow_warrior_.wvspitems.Listeners;

import me.shadow_warrior_.wvspitems.Holders.Cooldowns;
import me.shadow_warrior_.wvspitems.WvspItems;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class TotemListener extends Cooldowns implements Listener {
    private final WvspItems plugin;

    public TotemListener(WvspItems plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e){
        Player ppp = e.getPlayer();
        if(e.getPlayer().getItemInHand().getItemMeta() == null || e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null || (e.getPlayer().getItemInHand() == null)) return;
        if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Kostur Szamana") && e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(!getCooldown().containsKey(ppp.getUniqueId()) || System.currentTimeMillis() - getCooldown().get(ppp.getUniqueId()) >= 50000) {
                getCooldown().put(ppp.getUniqueId(), System.currentTimeMillis());
                ArmorStand armorStand = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.ARMOR_STAND);
                Vector vector = e.getPlayer().getLocation().getDirection();
                vector = vector.multiply(1.2);
                totem(armorStand, vector, Effect.HEART, Sound.VILLAGER_YES, "TOTEM UZDROWIENIA", Material.JUKEBOX, Material.POTION, false, ChatColor.RED, ChatColor.BOLD,false);
            }else   ppp.sendMessage("Musisz poczekac: " + ((50000 - (System.currentTimeMillis() - getCooldown().get(ppp.getUniqueId())))/1000) + " sekund, aby uzyc swojej umiejetnosci ponownie.");
        } else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Kostur Szamana") && e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            if(!getCooldown2().containsKey(ppp.getUniqueId()) || System.currentTimeMillis() - getCooldown2().get(ppp.getUniqueId()) >= 50000) {
                getCooldown2().put(ppp.getUniqueId(), System.currentTimeMillis());
                ArmorStand gravOrb = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.ARMOR_STAND);
                Vector vector = e.getPlayer().getLocation().getDirection();
                vector = vector.multiply(1.2);
                totem(gravOrb, vector, Effect.LARGE_SMOKE, Sound.ENDERMAN_SCREAM, "TOTEM ZGUBIENIA", Material.COAL_BLOCK, Material.OBSIDIAN, true, ChatColor.BLACK, ChatColor.BOLD,true);
            } else   ppp.sendMessage("Musisz poczekac: " + ((50000 - (System.currentTimeMillis() - getCooldown2().get(ppp.getUniqueId())))/1000) + " sekund, aby uzyc swojej umiejetnosci ponownie.");
        }
    }

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onArmorStandDestroy(EntityDamageEvent e){
        if(e.getEntity() instanceof ArmorStand) e.setCancelled(true);
    }
    public void totem(ArmorStand stand,Vector vector, Effect effect,Sound sound,String name,Material material1,Material material2,Boolean totem,ChatColor color,ChatColor color2,Boolean test){
        stand.setVelocity(vector);
        stand.setVisible(true);
        stand.setGravity(true);
        stand.setCustomName(color + "" + color2  + name);
        stand.setCustomNameVisible(true);
        stand.setHelmet(new ItemStack(material1));
        stand.setRightArmPose(new EulerAngle(1,1,1));
        stand.setItemInHand(new ItemStack(material2));
        if(!test) {
            setCount(0);
        }else setCount2(0);

        new BukkitRunnable(){
            @Override
            public void run() {
                if(!test && getCount() >= 200 || test && getCount2() >= 200){
                    stand.remove();
                    this.cancel();
                }else {
                    for (Entity ent : stand.getNearbyEntities(15,15,15)) {
                        if (ent.getType().equals(EntityType.PLAYER)) {
                            Player p = (Player) ent;
                            p.getWorld().spigot().playEffect(stand.getLocation(), effect);
                            p.playSound(stand.getLocation(), sound, 0.5F, 1);
                            if(p.hasPotionEffect(PotionEffectType.WATER_BREATHING) && !totem) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1, true));
                                p.removePotionEffect(PotionEffectType.BLINDNESS);
                                p.removePotionEffect(PotionEffectType.POISON);
                                p.removePotionEffect(PotionEffectType.SLOW);
                            } else if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION) && totem) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 30, 1, true));
                                ent.setVelocity(ent.getVelocity().clone().add(stand.getLocation().clone().toVector().subtract(ent.getLocation().clone().toVector()).multiply(0.005)));
                            }
                            if (p.getPlayer().getInventory().contains(Material.STICK) && p.isSneaking() && p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
                                if(!getCooldown3().containsKey(p.getUniqueId()) || System.currentTimeMillis() - getCooldown3().get(p.getUniqueId()) >= 5000) {
                                    getCooldown3().put(p.getUniqueId(), System.currentTimeMillis());
                                    p.setVelocity(p.getVelocity().clone().add(stand.getLocation().clone().toVector().subtract(p.getLocation().clone().toVector()).multiply(0.199).setY(0.6)));
                                }p.sendMessage("Musisz poczekac: " + ((5000 - (System.currentTimeMillis() - getCooldown3().get(p.getUniqueId())))/1000) + " aby uzyc swojej umiejetnosci ponownie");
                            }
                        }
                    }
                }
                if(!test) {
                    setCount(getCount() + 1);
                }else  setCount2(getCount2() + 1);
            }
        }.runTaskTimer(plugin,10,1);
    }

}
