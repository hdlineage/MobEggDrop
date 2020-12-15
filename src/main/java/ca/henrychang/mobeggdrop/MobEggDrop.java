package ca.henrychang.mobeggdrop;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobEggDrop extends JavaPlugin {

    MEDEventhandler eventHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getConsoleSender().sendMessage("MobEggDrop Plugin Version 1.0");
        getServer().getConsoleSender().sendMessage("MobEggDrop Plugin Starting...");

        loadConfig();

        eventHandler = new MEDEventhandler(this);
        getServer().getPluginManager().registerEvents(eventHandler, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("MobEggDrop Plugin Stopping...");
    }


    private void loadConfig(){
        FileConfiguration config = getConfig();
        for (Material key : Material.values()){
            String key_string = key.toString();
            if(!key_string.startsWith("LEGACY_") && key_string.endsWith("_SPAWN_EGG")){
                config.addDefault(key_string.replace("_SPAWN_EGG",""), 2.5);
            }
        }

        config.options().copyDefaults(true);
        saveConfig();
    }

    public boolean matchEntity(String entity){
        return getConfig().contains(entity);
    }
    public double getChance(String entity){
        return getConfig().getDouble(entity);
    }

}
