package land.builders.displaybuildtools.data.playerData;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlaceItemDisplayEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList ( ) ;
    private static World world;
    private static Location location;
    private static Player player;
    private static Material material;
    private static float yaw;
    private static

    public static HandlerList getHandlerList ( ) {
        return HANDLERS ;
    }

    @Override
    public HandlerList getHandlers ( ) {
        return HANDLERS ;
    }

    public PlaceItemDisplayEvent(World world, Location location,Player player;)
}
