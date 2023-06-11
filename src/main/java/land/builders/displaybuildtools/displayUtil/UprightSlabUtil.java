package land.builders.displaybuildtools.displayUtil;

import land.builders.displaybuildtools.data.playerData.PData;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

public class UprightSlabUtil {
//    public static void placeUtil(Player player){
//
//        World world = player.getWorld();
//        Location eyeLocation = player.getEyeLocation();
//        Vector direction = player.getEyeLocation().getDirection();
//
//        RayTraceResult rayTraceEntity = world.rayTraceEntities(eyeLocation, direction, 5, 0.5, p -> !player.getUniqueId().equals(p.getUniqueId()));
//
//        RayTraceResult rayTraceBlock = world.rayTraceBlocks(eyeLocation, direction, 5, FluidCollisionMode.ALWAYS, false);
//
//        if (rayTraceEntity != null && rayTraceBlock != null){
//            if (rayTraceEntity.getHitEntity().getLocation().distance(eyeLocation) < rayTraceBlock.getHitBlock().getLocation().distance(eyeLocation)){
//                uesItemDisplayLocation(rayTraceEntity,player);
//            } else {
//                useBlockLocation(rayTraceBlock,player);
//            }
//        } else if (rayTraceEntity != null){
//            uesItemDisplayLocation(rayTraceEntity,player);
//        } else {
//            useBlockLocation(rayTraceBlock,player);
//        }
//    }

    private static float getItemDisplayYaw(float playerYaw){
        playerYaw += 180;
        float itemDisplayYaw;
        if (playerYaw >= 45 && playerYaw < 135  ){
            itemDisplayYaw = -90;
        } else if (playerYaw >= 135 && playerYaw < 225){
            itemDisplayYaw = 0;
        } else if (playerYaw >= 225 && playerYaw < 315) {
            itemDisplayYaw = 90;
        } else {
            itemDisplayYaw = 180;
        }
        return itemDisplayYaw;
    }

    public static void useBlockLocation(RayTraceResult rayTraceResult, Player player){

        Location itemDisplayLocation = rayTraceResult.getHitBlock().getLocation();
        BlockFace blockFace = rayTraceResult.getHitBlockFace();

        if (blockFace.equals(BlockFace.DOWN)){
            itemDisplayLocation.add(0,-1,0);
        } else if (blockFace.equals(BlockFace.EAST)){
            itemDisplayLocation.add(1,0,0);
        } else if (blockFace.equals(BlockFace.NORTH)) {
            itemDisplayLocation.add(0,0,-1);
        } else if (blockFace.equals(BlockFace.SOUTH)) {
            itemDisplayLocation.add(0,0,1);
        } else if (blockFace.equals(BlockFace.WEST)) {
            itemDisplayLocation.add(-1,0,0);
        } else if (blockFace.equals(BlockFace.UP)) {
            itemDisplayLocation.add(0,1,0);
        }

        float itemDisplayYaw = getItemDisplayYaw(player.getLocation().getYaw());

        spawnItemDisplay(itemDisplayLocation,itemDisplayYaw,player);
    }



    public static void uesItemDisplayLocation(RayTraceResult rayTraceResult, Player player){

        int entityBlockX,entityBlockY,entityBlockZ;

        Double entityX,entityY,entityZ,hitPosX,hitPosY,hitPosZ;

        entityX = rayTraceResult.getHitEntity().getLocation().getX();
        entityY = rayTraceResult.getHitEntity().getLocation().getY();
        entityZ = rayTraceResult.getHitEntity().getLocation().getZ();

        entityBlockX = rayTraceResult.getHitEntity().getLocation().getBlockX();
        entityBlockY = rayTraceResult.getHitEntity().getLocation().getBlockY();
        entityBlockZ = rayTraceResult.getHitEntity().getLocation().getBlockZ();

        hitPosX = rayTraceResult.getHitPosition().getX();
        hitPosY = rayTraceResult.getHitPosition().getY();
        hitPosZ = rayTraceResult.getHitPosition().getZ();

        Location blockLocation = new Location(player.getWorld(), entityBlockX,entityBlockY,entityBlockZ);

        if (entityX-hitPosX == 0.5){
            blockLocation.add(-1,0,0);
        } else if (entityX-hitPosX == -0.5) {
            blockLocation.add(1,0,0);
        } else if (entityY-hitPosY == 0.5){
            blockLocation.add(0,-1,0);
        } else if (entityY-hitPosY == -0.5) {
            blockLocation.add(0,1,0);
        } else if (entityZ-hitPosZ == 0.5){
            blockLocation.add(0,0,-1);
        } else if (entityZ-hitPosZ == -0.5) {
            blockLocation.add(0,0,1);
        }

        float itemDisplayYaw = getItemDisplayYaw(player.getLocation().getYaw());

        spawnItemDisplay(blockLocation,itemDisplayYaw,player);
    }

    private static void spawnItemDisplay(Location location,float yaw,Player player){
        location.add(0.5,0.5,0.5);

        ItemDisplay itemDisplay = (ItemDisplay) player.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        ItemStack itemStack = new ItemStack(PData.getSlabMaterial(player.getUniqueId()));
        itemDisplay.setItemStack(itemStack);
        itemDisplay.setRotation(yaw,-90);
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE,1f,1f);
    }
}
