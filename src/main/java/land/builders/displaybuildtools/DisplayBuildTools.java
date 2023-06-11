package land.builders.displaybuildtools;

import land.builders.displaybuildtools.commands.DisplayBuildToolCommand;
import land.builders.displaybuildtools.gui.ItemWindow;
import land.builders.displaybuildtools.listeners.BlockBreakListener;
import land.builders.displaybuildtools.listeners.LeftClickListener;
import land.builders.displaybuildtools.listeners.PlayerQuitListener;
import land.builders.displaybuildtools.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisplayBuildTools extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListener();
        ItemWindow.buildGUI();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("uprightslabtool").setExecutor(new DisplayBuildToolCommand());
    }

    private void registerListener() {
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeftClickListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
    }
}
