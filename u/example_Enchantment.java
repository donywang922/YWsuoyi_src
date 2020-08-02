package u.u;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Hand;

public class example_Enchantment extends Enchantment {


    protected example_Enchantment() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 2 * level, level - 1));


            if (target instanceof PlayerEntity) {
                for (int i = 0; i < 36; i++) {
                    if (((PlayerEntity) target).inventory.main.get(i).getItem() instanceof ToolItem) {
                        ((PlayerEntity) target).inventory.main.set(i, new ItemStack(Items.OAK_SAPLING, 1));
                    }
                }
            }


        }

        super.onTargetDamaged(user, target, level);
    }
}
