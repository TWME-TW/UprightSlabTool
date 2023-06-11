package land.builders.displaybuildtools.commands;

import land.builders.displaybuildtools.gui.ItemWindow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DisplayBuildToolCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("只有玩家可以使用");
            return false;
        }
        Player player = (Player) sender;
        if (player.hasPermission("uprightslabtool.use")){
            ItemWindow.openWindow(player);
            return true;
        }

        return false;
    }
}
