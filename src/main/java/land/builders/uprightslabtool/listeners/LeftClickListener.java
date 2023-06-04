package land.builders.uprightslabtool.listeners;

import land.builders.uprightslabtool.gui.slab.GUI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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

        if (event.getItem() == null || !event.getItem().getType().equals(Material.BONE))
            return;

        event.setCancelled(true);

        if (!player.hasPermission("uprightslabtool.use")){
            return;
        }

        if (player.isSneaking()) {
            GUI.openWindow(player);
            return;
        }

        World world = player.getWorld();
        Location eyeLocation = player.getEyeLocation();
        Vector direction = player.getEyeLocation().getDirection();
        RayTraceResult rayTraceResult = world.rayTraceEntities(eyeLocation,direction,5,0.6, p -> !player.getUniqueId().equals(p.getUniqueId()));
        if (rayTraceResult != null){
            if (rayTraceResult.getHitEntity().getType().equals(EntityType.ITEM_DISPLAY)){
                rayTraceResult.getHitEntity().remove();
                player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK,1f,1f);
            }
        }
    }
}
