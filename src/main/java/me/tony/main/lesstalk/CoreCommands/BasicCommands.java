package me.tony.main.lesstalk.CoreCommands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tony.main.lesstalk.AdminGUI.AdminMenu;
import me.tony.main.lesstalk.LessTalk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BasicCommands implements CommandExecutor {

    private LessTalk plugin;
    public BasicCommands(LessTalk plugin) {
        this.plugin = plugin;
    }

    public static ArrayList<String> staffChat = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        String prefix = this.plugin.getConfig().getString("Prefix");
        String noPerm = this.plugin.getConfig().getString("No Permission");
        String chatLocked = this.plugin.getConfig().getString("Chat Locked");
        String chatUnlocked = this.plugin.getConfig().getString("Chat Unlocked");
        String staffPerm = this.plugin.getConfig().getString("Staff Permission");
        String usageMsg = this.plugin.getConfig().getString("Basic Usage Message");
        String chatCleared = this.plugin.getConfig().getString("Chat Cleared");
        String staffEnabled = this.plugin.getConfig().getString("Staff Chat Enabled");
        String staffDisabled = this.plugin.getConfig().getString("Staff Chat Disabled");
        String cmdDis = this.plugin.getConfig().getString("Command Disabled");

        if (chatLocked != null) {
            chatLocked = PlaceholderAPI.setPlaceholders(p, chatLocked);
        }
        if (chatUnlocked != null) {
            chatUnlocked = PlaceholderAPI.setPlaceholders(p, chatUnlocked);
        }
        if (chatCleared != null) {
            chatCleared = PlaceholderAPI.setPlaceholders(p, chatCleared);
        }

        Boolean lockedChat = this.plugin.getConfig().getBoolean("Chat Disabled");

        Boolean chatLock = this.plugin.getConfig().getBoolean("Chat Lock");
        Boolean chatClear = this.plugin.getConfig().getBoolean("Clear Chat");
        Boolean staffCommand = this.plugin.getConfig().getBoolean("Staff Command");


        if (p.hasPermission(staffPerm)) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bLessTalk help menu"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/lta &b| &7Shows the Administration Menu."));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/lt lock &b| &7Lock/Unlock the chat"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/lt clear &b| &7Clear the chat"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/lt chat &b| &7Toggle staff chat on/off"));
            }
            if (args.length == 1) {
                String subCommand = args[0];
                if (subCommand.isEmpty()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + usageMsg));
                }
                if (subCommand.equalsIgnoreCase("lock")) {
                    if (chatLock.equals(true)) {
                        if (lockedChat.equals(false)) {
                            this.plugin.getConfig().set(chatLocked, true);
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + chatLocked));
                        } else if (lockedChat.equals(true)) {
                            this.plugin.getConfig().set(chatLocked, false);
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + chatUnlocked));
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + cmdDis));
                    }
                }
                if (subCommand.equalsIgnoreCase("clear")) {
                    if (chatClear.equals(true)) {
                        for (int i = 0; i < 200; i++) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + chatCleared));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + cmdDis));
                    }
                }
                if (subCommand.equalsIgnoreCase("chat")) {
                    if (staffCommand.equals(true)) {
                        if (!staffChat.contains(p.getName())) {
                            staffChat.add(p.getName());
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + staffEnabled));
                        } else if (staffChat.contains(p.getName())) {
                            staffChat.remove(p.getName());
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + staffDisabled));
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + cmdDis));
                    }
                }
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + noPerm));
        }
        return true;
    }
}
