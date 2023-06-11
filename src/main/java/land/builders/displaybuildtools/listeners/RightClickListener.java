package land.builders.displaybuildtools.listeners;

import land.builders.displaybuildtools.displayUtil.UprightSlabUtil;
import land.builders.displaybuildtools.gui.ItemWindow;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RightClickListener implements Listener {
    private static Map<UUID, Long> cooldown = new HashMap<>();
    @EventHandler(
            priority = EventPriority.LOW
    )
    public void onLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getOpenInventory().getTopInventory().getType() == InventoryType.CHEST)
            return;
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;
        if (event.getItem() == null || !event.getItem().getType().equals(Material.BONE))
            return;
        if (!player.hasPermission("displaybuildtools.use"))
            return;

        event.setCancelled(true);

        if (player.isSneaking()) {
            ItemWindow.openWindow(player);
            return;
        }

        long currentTime = System.currentTimeMillis();
        if(cooldown.containsKey(player.getUniqueId())){
            long lastPlaceTime = cooldown.get(player.getUniqueId());
            double timeSinceLastPlace = (double) (currentTime - lastPlaceTime) / 1000;
            if (timeSinceLastPlace < 0.6){
                return;
            }
        }

        World world = player.getWorld();
        Location eyeLocation = player.getEyeLocation();
        Vector direction = player.getEyeLocation().getDirection();

        RayTraceResult rayTraceEntity = world.rayTraceEntities(eyeLocation,direction,5,0.6, p -> !player.getUniqueId().equals(p.getUniqueId()));
        RayTraceResult rayTraceBlock = world.rayTraceBlocks(eyeLocation, direction, 5, FluidCollisionMode.ALWAYS, false);
        if (rayTraceEntity != null && rayTraceBlock != null){
            if (rayTraceEntity.getHitEntity().getLocation().distance(eyeLocation) < rayTraceBlock.getHitBlock().getLocation().distance(eyeLocation)){
                UprightSlabUtil.uesItemDisplayLocation(rayTraceEntity,player);
            } else {
                UprightSlabUtil.useBlockLocation(rayTraceBlock,player);
            }
        } else if (rayTraceEntity != null){
            UprightSlabUtil.uesItemDisplayLocation(rayTraceEntity,player);
        } else {
            UprightSlabUtil.useBlockLocation(rayTraceBlock,player);
        }
//        ItemDisplayUtil.placeUtil(player);
        cooldown.put(player.getUniqueId(),currentTime);
    }
}
