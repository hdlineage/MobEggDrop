package ca.henrychang.mobeggdrop;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class MEDEventhandler implements Listener {
    MobEggDrop plugin;

    public MEDEventhandler(MobEggDrop plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event){
        EntityType type = event.getEntityType();
        if(!plugin.matchEntity(type.toString())) return;

        double chance = plugin.getChance(type.toString());

        if(chance <= 0 || chance >100) return;

        double random = ThreadLocalRandom.current().nextDouble(0, 100);

        if(chance < random) return;

        String eggName = type.toString()+"_SPAWN_EGG";

        ItemStack egg = new ItemStack(Material.valueOf(eggName));

        event.getDrops().add(egg);

    }

}
