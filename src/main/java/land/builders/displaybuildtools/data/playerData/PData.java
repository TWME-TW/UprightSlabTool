package land.builders.displaybuildtools.data.playerData;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.RayTraceResult;

import java.util.UUID;

public class PData {

    private Material material = null;
    private boolean uprightSlabMode = true;
    private boolean littleBigMode = false;
    private UUID playerUUID;
    private Location blockLocation = null;


    public PData(UUID uuid) {
        this.playerUUID = uuid;
    }

    public PData setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public PData setUprightSlabMode(boolean mode) {
        this.uprightSlabMode = mode;
        return this;
    }

    public boolean getUprightSlabMode() {
        return this.uprightSlabMode;
    }

    public Material getMaterial() {
        if (material == null) {
            material = Material.STONE_SLAB;
        }
        return this.material;
    }

    public PData setLittleBigMode(boolean mode) {
        this.littleBigMode = mode;
        return this;
    }

    public void setBlockLocation(RayTraceResult blockRT, RayTraceResult entityRT) {

    }

    public boolean existMaterial() {
        return (material != null);
    }
}
