package land.builders.displaybuildtools.gui;

import land.builders.displaybuildtools.data.playerData.PData;
import land.builders.displaybuildtools.data.playerData.PManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class MaterialItem extends AbstractItem {
    private Material material;


    public MaterialItem(Material material){
        this.material = material;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(material);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT,1f,1f);
        PData pData = PManager.getPlayerData(player.getUniqueId());
        pData.setMaterial(material);
        player.closeInventory();
    }
}
