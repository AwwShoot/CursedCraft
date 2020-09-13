package net.cursedCraft.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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
            if(world.getBlockState(new BlockPos(this.getX(), this.getY(), this.getZ())).isAir()&&world.getBlockState(new BlockPos(this.getX(), this.getY()+1, this.getZ())).isAir()){

                zombie.updatePosition(this.getX(), this.getY(), this.getZ());
                //Zombies spawned no longer need to be initialized manually it seems, but they do still need a position manually set
                world.spawnEntity(zombie);

            }

        }
    }
}
