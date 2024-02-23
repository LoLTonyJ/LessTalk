package me.tony.main.lesstalk.AdminGUI;

import me.tony.main.lesstalk.LessTalk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AdminMenu implements CommandExecutor {

    private LessTalk plugin;
    public AdminMenu(LessTalk plugin) {
        this.plugin = plugin;
    }

    protected ItemStack createGUIItem(final Material material, final String title, final String lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
        meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', lore)));
        item.setItemMeta(meta);

        return item;
    }


    public Inventory AdminGUI(Player player) {

        String prefix = this.plugin.getConfig().getString("Prefix");

        Boolean chatLock = this.plugin.getConfig().getBoolean("Chat Lock");
        Boolean clearChat = this.plugin.getConfig().getBoolean("Clear Chat");
        Boolean staffChat = this.plugin.getConfig().getBoolean("Staff Command");
        Boolean bypassChatL = this.plugin.getConfig().getBoolean("Bypass Chat Lock");

        Inventory inv = Bukkit.createInventory(player, 54, ChatColor.translateAlternateColorCodes('&', prefix + " &c&lAdministration Menu"));
        inv.setItem(9, createGUIItem(Material.PAPER, "&bChat Lock", "&cEnable / Disable /lt lock"));
        if (chatLock.equals(true)) {
            inv.setItem(18, createGUIItem(Material.LIME_DYE, "&aEnabled", "&cClick me to Disable!"));
        } else {
            inv.setItem(18, createGUIItem(Material.RED_DYE, "&cDisabled", "&aClick me to Enable!"));
        }
        inv.setItem(12, createGUIItem(Material.PAPER, "&bClear Chat", "&cEnable / Disable /lt clear"));
        if (clearChat.equals(true)) {
            inv.setItem(21, createGUIItem(Material.LIME_DYE, "&aEnabled", "&cClick me to Disable!"));
        } else {
            inv.setItem(21, createGUIItem(Material.RED_DYE, "&cDisabled", "&aClick me to Enable!"));
        }
        inv.setItem(15, createGUIItem(Material.PAPER, "&bClear Chat", "&cEnable / Disable /lt staff"));
        if (staffChat.equals(true)) {
            inv.setItem(24, createGUIItem(Material.LIME_DYE, "&aEnabled", "&cClick me to Disable!"));
        } else {
            inv.setItem(24, createGUIItem(Material.RED_DYE, "&cDisabled", "&aClick me to Enable!"));
        }
        inv.setItem(39, createGUIItem(Material.PAPER, "&bBypass Chat Lock", "&cEnabled / Disable the Bypass Chat Lock Function"));
        if (bypassChatL.equals(true)) {
            inv.setItem(48, createGUIItem(Material.LIME_DYE, "&aEnabled", "&cClick me to Disable!"));
        } else {
            inv.setItem(48, createGUIItem(Material.RED_DYE, "&cDisabled", "&aClick me to Enable!"));
        }

        player.openInventory(inv);

        return inv;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        String prefix = this.plugin.getConfig().getString("Prefix");
        String adminPerm = this.plugin.getConfig().getString("Admin Permission");
        String noPerm = this.plugin.getConfig().getString("No Permission");

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(adminPerm)) {
                AdminGUI(p);
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + noPerm));
            }
        }
        return true;
    }
}
