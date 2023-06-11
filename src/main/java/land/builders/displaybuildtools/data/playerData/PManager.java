package land.builders.displaybuildtools.data.playerData;

import land.builders.displaybuildtools.data.playerData.PData;
import org.bukkit.Material;

import java.util.Map;
import java.util.UUID;

public class PManager {
    private static Map<UUID, PData> PlayerMap;

    public static void placeItemDisplay(UUID uuid){
        if (!PlayerMap.containsKey(uuid)){
            return;
        }
        PData data= PlayerMap.get(uuid);
        if (!data.existMaterial()){
            return;
        }
        Material material = data.getMaterial();


    }
    public static PData getPlayerData(UUID uuid){
        if (!PlayerMap.containsKey(uuid)){
            PlayerMap.put(uuid,new PData(uuid));
        }
        return PlayerMap.get(uuid);
    }

    public static void removePData(UUID uuid){
        PlayerMap.remove(uuid);
    }
}
