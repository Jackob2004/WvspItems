package me.shadow_warrior_.wvspitems.Holders;

import java.util.HashMap;
import java.util.UUID;

public class Cooldowns {
    private final HashMap<UUID, Long> cooldown = new HashMap<>();
    private final HashMap<UUID, Long> cooldown2 = new HashMap<>();
    private final HashMap<UUID, Long> cooldown3 = new HashMap<>();

    private final HashMap<UUID, Long> cooldown4 = new HashMap<>();

    private final HashMap<UUID, Long> cooldown5 = new HashMap<>();


    public HashMap<UUID, Long> getCooldown() {
        return cooldown;
    }

    public HashMap<UUID, Long> getCooldown2() {
        return cooldown2;
    }

    public HashMap<UUID, Long> getCooldown3() {
        return cooldown3;
    }

    public HashMap<UUID, Long> getCooldown4() {
        return cooldown4;
    }

    public HashMap<UUID, Long> getCooldown5() {
        return cooldown5;
    }

    private int count = 0;

    public int getCount() {
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }

    private int count2 = 0;

    public int getCount2() {
        return count2;
    }
    public void setCount2(int count2){
        this.count2 = count2;
    }
}
