package kheeto.chatmanager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class ChatManager extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CM.setupCooldown();

        getCommand("cmreload").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);

        getServer().getConsoleSender().sendMessage("\n\n"+ChatColor.GREEN+"[ChatManager] Plugin has been enabled!\n\n");

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("\n\n"+ChatColor.RED+"[ChatManager] Plugin has been disabled!\n\n");
    }

    public void loadConfig() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        reloadConfig();


    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        String msg = e.getMessage();
        List<String> words = getConfig().getStringList("BlacklistedWords");
        boolean hasSweared = false;


        if(!getConfig().getBoolean("blacklistWords")) {
            for (int i = 0; i < words.size(); i++) {
                if (msg.contains(words.get(i)) && !(e.getPlayer().hasPermission("cm.bypass.blacklist"))) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getConfig().getString("Messages.Blacklisted"));
                    hasSweared = true;
                }
            }
        }

        if (!getConfig().getBoolean("enableCooldown")) {


            if (!hasSweared && !(e.getPlayer().hasPermission("cm.bypass.cooldown"))) {
                if (CM.checkCooldown(e.getPlayer())) {
                    CM.setCooldown(e.getPlayer(), getConfig().getInt("Cooldown"));
                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getConfig().getString("Messages.Cooldown") + CM.getCooldown(e.getPlayer()) + " seconds");
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            if (sender.hasPermission("cm.reload")) {

                loadConfig();

                sender.sendMessage(getConfig().getString("Messages.Config"));

                return true;
            }

            sender.sendMessage(getConfig().getString("Messages.NoPermission"));
            return true;

        } else {
            getServer().getConsoleSender().sendMessage(getConfig().getString("Messages.SenderIsConsole"));
            return true;
        }


    }

    public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        return null;

    }
}
