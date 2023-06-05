package land.builders.uprightslabtool.listeners;

import land.builders.uprightslabtool.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (PlayerData.playerSlabMaterial.containsKey(player.getUniqueId())){
            PlayerData.playerSlabMaterial.remove(player.getUniqueId());
        }
    }
}
