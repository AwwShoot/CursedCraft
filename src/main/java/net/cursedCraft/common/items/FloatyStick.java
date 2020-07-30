package net.cursedCraft.common.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.Random;
import java.util.function.Consumer;

import static net.minecraft.entity.effect.StatusEffects.LEVITATION;

public class FloatyStick extends Item {
    public FloatyStick(Settings settings) {
        super(settings);
    }
    /**
     * The damage method casts the lambda as Consumer in source code, but that's just an artifact. Ignore the cast and it works fine.
     */
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        stack.damage(1, user, p -> p.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        entity.addStatusEffect(new StatusEffectInstance(LEVITATION, 10000000, 4));
        return ActionResult.SUCCESS;
    }
}
