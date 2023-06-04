package land.builders.uprightslabtool.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler(
            ignoreCancelled = true,
            priority = EventPriority.LOWEST
    )
    public void onBlockBreakEvent(BlockBreakEvent event) {

        boolean isBONE = event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BONE);
        Player player = event.getPlayer();

        if (!player.hasPermission("uprightslabtool.use"))
            return;
        if (isBONE)
            event.setCancelled(true);
    }
}
