package u.u;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Mod implements net.fabricmc.api.ModInitializer {
    public static final block_with_screen_without_entity eb = new block_with_screen_without_entity(FabricBlockSettings.of(Material.STONE));
    public static final bag_item i1 = new bag_item(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
    public static final ScreenHandlerType<screenhandler> CT = ScreenHandlerRegistry.registerSimple(new Identifier("my_mod", "ct"), screenhandler::new);
    private static final Enchantment FROST = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("tutorial", "frost"),
            new example_Enchantment()
    );
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM,new Identifier("aa","aa"),i1);
        Registry.register(Registry.BLOCK,new Identifier("aa","bb"),eb);
        Registry.register(Registry.ITEM,new Identifier("aa","bb"),new BlockItem(eb,new Item.Settings().group(ItemGroup.MISC)));
    }
}
