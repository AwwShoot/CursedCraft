package net.cursedCraft.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends Entity {


    @Shadow private RegistryKey<World> spawnPointDimension;

    @Shadow private boolean spawnPointSet;

    @Shadow private BlockPos spawnPointPosition;

    @Shadow public abstract void setSpawnPoint(RegistryKey<World> dimension, BlockPos pos, boolean spawnPointSet, boolean bl);

    public ServerPlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at=@At("RETURN"), method="onDeath", cancellable = true)
    public void goToHell(DamageSource source, CallbackInfo ci){
        BlockPos pos=null;
        BlockPos spawnPoint=spawnPointPosition;
        int tries=0;
        do{

            if(spawnPoint==null) {
                pos = new BlockPos(random.nextInt(16000) - 8000, random.nextInt(100)+20, random.nextInt(16000) - 8000);
            }else{
                pos = spawnPoint.add(random.nextInt(16000)-8000,0,random.nextInt(16000)-8000);
            }
            if(pos==null)
                pos = new BlockPos(random.nextInt(16000) - 8000, random.nextInt(100)+20, random.nextInt(16000) - 8000);
            for(int y=pos.getY(); y>100;y--){
                pos=pos.add(0,-1,0);
            }
            for(int x=pos.getY(); x<30;x++){
                pos=pos.add(0,1,0);
            }


            tries++;

            tries++;
        }while(findLivableSpace(pos)==null&&tries<10);
        this.setSpawnPoint(World.NETHER, findLivableSpace(pos)==null ? pos: findLivableSpace(pos), true, true);
        System.out.println(spawnPointPosition.getY());


    }

    public BlockPos findLivableSpace(BlockPos start){
        if(start==null){
            return null;
        }
        int startY=start.getY();
        int startX=start.getX();

        int startZ=start.getZ();
        for(int x=-7; x<8; x++){
            for(int z=0; z<8;z++){
                for(int y=-7; y<8;y++){
                    if(startY+y<32||startY+y>120)
                        continue;
                    
                    BlockPos pos= new BlockPos(startX+x,startY+y, startZ+z);
                    if(world.getBlockState(pos).isAir()&&world.getBlockState(pos.up(1)).isAir()&&world.getBlockState(pos.down(1)).isFullCube(world, pos)){
                        return pos;
                    }
                }
            }
        }
        return null;
    }

}
