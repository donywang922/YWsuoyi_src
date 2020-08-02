package u.u;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.world.World;

import java.util.List;

public class screenhandler extends ForgingScreenHandler {
    private final World world;
    private SmithingRecipe recipe;
    private final List<SmithingRecipe> recipeList;

    public screenhandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public screenhandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Mod.CT, syncId, playerInventory, context);
        this.world = playerInventory.player.world;
        this.recipeList = this.world.getRecipeManager().method_30027(RecipeType.SMITHING);
    }

    protected boolean canUse(BlockState state) {
        return state.isOf(Mod.eb);
    }

    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return this.recipe != null && this.recipe.matches(this.input, this.world);
    }

    protected ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        this.method_29539(0);
        this.method_29539(1);
        this.context.run((world, blockPos) -> {
            world.syncWorldEvent(1044, blockPos, 0);
        });
        return stack;
    }

    private void method_29539(int i) {
        ItemStack itemStack = this.input.getStack(i);
        itemStack.decrement(1);
        this.input.setStack(i, itemStack);
    }

    public void updateResult() {
        List<SmithingRecipe> list = this.world.getRecipeManager().getAllMatches(RecipeType.SMITHING, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            this.recipe = (SmithingRecipe)list.get(0);
            ItemStack itemStack = this.recipe.craft(this.input);
            this.output.setStack(0, itemStack);
        }

    }

    protected boolean method_30025(ItemStack itemStack) {
        return this.recipeList.stream().anyMatch((smithingRecipe) -> smithingRecipe.method_30029(itemStack));
    }
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = super.transferSlot(player, index);
        System.out.println(itemStack);
        return ItemStack.EMPTY;
    }
}
