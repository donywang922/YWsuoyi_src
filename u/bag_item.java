package u.u;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class bag_item extends Item {
    public static final TranslatableText CONTAINER_NAME = new TranslatableText("container.enderchest");
    public bag_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        bag_inventory modInventory = new bag_inventory(user.getStackInHand(hand));
        CompoundTag tag = user.getStackInHand(hand).getOrCreateTag();
        modInventory.readTags(tag.getList("i",10));
        user.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, modInventory), CONTAINER_NAME));
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
