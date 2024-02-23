package me.tony.main.lesstalk;

import me.tony.main.lesstalk.AdminGUI.AdminMenu;
import me.tony.main.lesstalk.CoreCommands.BasicCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LessTalk extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("PlaceHolderAPI") != null) {
            System.out.println("\n LessTalk has been enabled without any issues");
            System.out.println("If an issue occurs please feel free to join our Discord");
            System.out.println("https://discord.gg/Z7nTTcwZat \n");

            getCommand("lesstalk").setExecutor(new BasicCommands(this));
            getCommand("lesstalkadmin").setExecutor(new AdminMenu(this));

        } else {
            getLogger().warning("PlaceHolderAPI was not found! Please install and restart!");
            getServer().getPluginManager().disablePlugin(this);
        }

    }
}
