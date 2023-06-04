package land.builders.uprightslabtool;

import land.builders.uprightslabtool.commands.UprightSlabToolCommand;
import land.builders.uprightslabtool.gui.slab.GUI;
import land.builders.uprightslabtool.listeners.BlockBreakListener;
import land.builders.uprightslabtool.listeners.LeftClickListener;
import land.builders.uprightslabtool.listeners.PlayerQuitListener;
import land.builders.uprightslabtool.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UprightSlabTool extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListener();
        GUI.buildGUI();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("uprightslabtool").setExecutor(new UprightSlabToolCommand());
    }

    private void registerListener() {
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeftClickListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
    }
}
