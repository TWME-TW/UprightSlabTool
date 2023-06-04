package land.builders.uprightslabtool.gui.slab;

import org.bukkit.Material;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

public class ForwardItem extends PageItem {

    public ForwardItem() {
        super(true);
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        ItemBuilder builder = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE);
        builder.setDisplayName("§7下一頁")
                .addLoreLines(gui.hasNextPage()
                        ? "§7跳到頁面 §e" + (gui.getCurrentPage() + 2) + "§7/§e" + gui.getPageAmount()
                        : "§c你已經翻到底了");

        return builder;
    }

}
