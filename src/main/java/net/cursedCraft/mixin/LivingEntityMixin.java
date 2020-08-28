package net.cursedCraft.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity{

    @Shadow public int deathTime=0;



    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at=@At("RETURN"), method="updatePostDeath", cancellable = true)
    protected void undeadDontDie(CallbackInfo ci){
        if(this.getType().equals(EntityType.ZOMBIE)&&deathTime==19){
            ZombieEntity zombie=new ZombieEntity(EntityType.ZOMBIE, world);
            zombie.updatePosition(this.getX(), this.getY(), this.getZ());

            world.spawnEntity(zombie);
            zombie.initialize(world, world.getLocalDifficulty(zombie.getBlockPos()), SpawnReason.SPAWNER , (EntityData)null, (CompoundTag)null);

        }
    }
}
