package net.cursedCraft.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import static net.minecraft.entity.effect.StatusEffects.LEVITATION;

public class FloatyStick extends Item {
    public FloatyStick(Settings settings) {
        super(settings);
    }

    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.addStatusEffect(new StatusEffectInstance(LEVITATION, 10000000, 4));
        return ActionResult.SUCCESS;
    }
}
