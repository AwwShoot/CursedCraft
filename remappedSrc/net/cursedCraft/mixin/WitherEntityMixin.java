package net.cursedCraft.mixin;

import com.google.common.collect.Maps;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(WitherEntity.class)
public abstract class WitherEntityMixin extends LivingEntity{



    public WitherEntityMixin(EntityType<? extends WitherEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Aww_Shoot. This allows withers to have status effects applied to them.
     */
    @Overwrite
    public boolean addStatusEffect(StatusEffectInstance effect) {
        return super.addStatusEffect(effect);

    };
}
