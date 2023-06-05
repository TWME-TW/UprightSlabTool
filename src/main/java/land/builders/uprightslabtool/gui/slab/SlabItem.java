package land.builders.uprightslabtool.gui.slab;

import land.builders.uprightslabtool.data.PlayerData;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class SlabItem extends AbstractItem {
    private Material material;


    public SlabItem(Material material){
        this.material = material;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(material);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT,1f,1f);
        PlayerData.playerMaterial.put(player.getUniqueId(), material);
        player.closeInventory();
    }
}
