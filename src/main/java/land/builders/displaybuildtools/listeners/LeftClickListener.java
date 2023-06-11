package land.builders.displaybuildtools.listeners;

import land.builders.displaybuildtools.displayUtil.UprightSlabUtil;
import land.builders.displaybuildtools.gui.ItemWindow;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;


public class LeftClickListener implements Listener {
    @EventHandler(
            priority = EventPriority.LOW
    )
    public void onLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getOpenInventory().getTopInventory().getType() == InventoryType.CHEST)
            return;
        if (!event.getAction().equals(Action.LEFT_CLICK_AIR) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK))
            return;

        if (!player.hasPermission("displaybuildtools.use")){
            return;
        }

        event.setCancelled(true);

        if (player.isSneaking()) {
            ItemWindow.openWindow(player);
            return;
        }

        World world = player.getWorld();
        Location eyeLocation = player.getEyeLocation();
        Vector direction = player.getEyeLocation().getDirection();

        RayTraceResult entityRT = world.rayTraceEntities(eyeLocation,direction,5,0.6, p -> !player.getUniqueId().equals(p.getUniqueId()));
        RayTraceResult blockRT = world.rayTraceBlocks(eyeLocation, direction, 5, FluidCollisionMode.ALWAYS, false);

        if (entityRT != null && blockRT != null){
            if (entityRT.getHitEntity().getLocation().distance(eyeLocation) < blockRT.getHitBlock().getLocation().distance(eyeLocation)){
                entityRT.getHitEntity().remove();
                player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK,1f,1f);
            }
        } else if (entityRT != null){
            entityRT.getHitEntity().remove();
            player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK,1f,1f);
        }
    }
}
