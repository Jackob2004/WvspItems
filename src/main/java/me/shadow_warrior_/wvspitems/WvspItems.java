package me.shadow_warrior_.wvspitems;

import me.shadow_warrior_.wvspitems.Commands.AtomowkaCommand;
import me.shadow_warrior_.wvspitems.Commands.TotemCommand;
import me.shadow_warrior_.wvspitems.Listeners.AtomowkaListener;
import me.shadow_warrior_.wvspitems.Listeners.BuffaloHorn;
import me.shadow_warrior_.wvspitems.Listeners.GandalfStaff;
import me.shadow_warrior_.wvspitems.Listeners.TotemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WvspItems extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TotemListener(this),this);
        getServer().getPluginManager().registerEvents(new GandalfStaff(),this);
        getServer().getPluginManager().registerEvents(new BuffaloHorn(),this);
        getServer().getPluginManager().registerEvents(new AtomowkaListener(),this);
        getCommand("tot435236").setExecutor(new TotemCommand());
        getCommand("a34534").setExecutor(new AtomowkaCommand());
    }

}
