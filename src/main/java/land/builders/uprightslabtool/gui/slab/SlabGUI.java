package land.builders.uprightslabtool.gui.slab;

import land.builders.uprightslabtool.gui.BackItem;
import land.builders.uprightslabtool.gui.ForwardItem;
import land.builders.uprightslabtool.gui.ListItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SlabGUI {
    private static Gui gui;

    public static void buildGUI () {
        Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r"));

        List<Item> items = Arrays.stream(Material.values())
                .filter(material -> material.name().toLowerCase().matches(".*slab+"))
                .map(ListItem::new)
                .collect(Collectors.toList());

        gui = PagedGui.items()
                .setStructure(
                        "x x x x x x x x <",
                        "x x x x x x x x #",
                        "x x x x x x x x #",
                        "x x x x x x x x #",
                        "x x x x x x x x >")
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL) // where paged items should be put
                .addIngredient('#', border)
                .addIngredient('<', new BackItem())
                .addIngredient('>', new ForwardItem())
                .setContent(items)
                .build();
    }

    public static void openWindow(Player player){
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN,1f,1f);
        Window window = Window.single()
                .setViewer(player)
                .setTitle("直立半磚選單")
                .setGui(gui)
                .build();
        window.open();
    }
}
