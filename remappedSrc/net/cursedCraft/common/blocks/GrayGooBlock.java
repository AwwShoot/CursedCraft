package net.cursedCraft.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Position;

import java.util.List;
import java.util.Random;

public class GrayGooBlock extends GooBlockBase {
    public GrayGooBlock(Settings settings) {
        super(settings.ticksRandomly().strength(0.5F));
    }
    public boolean hasRandomTicks(BlockState state) {
        return this.randomTicks;
    }


    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random){

        this.random= world.random;
        this.world=world;
        this.pos=pos;
        List<ServerPlayerEntity> playersIn5 = world.getNonSpectatingEntities(ServerPlayerEntity.class, new Box(pos).expand(5.0));
        List<ServerPlayerEntity> playersIn32 = world.getNonSpectatingEntities(ServerPlayerEntity.class, new Box(pos).expand(16.0));
        if(!playersIn5.isEmpty()){
            if(canBeBold()){
                growBold( playersIn5);
                return;
            }else{
                growCowardly(playersIn5);
                return;
                }
            }
        if(!playersIn32.isEmpty()){
            growChasing(playersIn32);
            return;
        }
        this.grow();
    }

    public boolean canBeBold(){
        int lowX= pos.getX()-3;
        int lowY= pos.getY()>3 ? pos.getY()-3: 0;
        int lowZ= pos.getZ()-3;
        int highX=pos.getX()+3;
        int highY=pos.getY()<253 ? pos.getY()+3: 255;
        int highZ=pos.getZ()+3;
        int gooCount=0;
        for(int x=lowX; x<=highX; x++){
            for(int y=lowY; y<=highY; y++){
                for(int z=lowZ;z<=highZ;z++){
                    if(world.getBlockState(new BlockPos(x,y,z)).equals(this.getDefaultState())){
                        gooCount++;
                        if(gooCount>49)
                            return true;
                    }

                }
            }
        }
        return false;

    }

    public boolean canBeCowardly(){
        int lowX= pos.getX()-3;
        int lowY= pos.getY()>3 ? pos.getY()-3: 0;
        int lowZ= pos.getZ()-3;
        int highX=pos.getX()+3;
        int highY=pos.getY()<253 ? pos.getY()+3: 255;
        int highZ=pos.getZ()+3;
        int gooCount=0;
        for(int x=lowX; x<=highX; x++){
            for(int y=lowY; y<=highY; y++){
                for(int z=lowZ;z<=highZ;z++){
                    if(world.getBlockState(new BlockPos(x,y,z)).equals(this.getDefaultState())){
                        gooCount++;
                        if(gooCount<9)
                            return true;
                    }

                }
            }
        }
        return false;
    }







    public void growBold( List<ServerPlayerEntity> players){


        if(players.isEmpty()){
            grow();

        }else{
            ServerPlayerEntity closestPlayer = getClosestPlayer(players, pos);
            boolean xDir=closestPlayer.getX()>=pos.getX();
            boolean yDir=closestPlayer.getY()>=pos.getY();
            boolean zDir=closestPlayer.getZ()>=pos.getZ();
            grow( xDir, yDir, zDir, 2);
        }

    }

    public void growChasing(List<ServerPlayerEntity> players){
        if(players.isEmpty()){
            grow();
        }else{
            ServerPlayerEntity closestPlayer = getClosestPlayer(players, pos);
            boolean xDir=closestPlayer.getX()>=pos.getX();
            boolean yDir=closestPlayer.getY()>=pos.getY();
            boolean zDir=closestPlayer.getZ()>=pos.getZ();
            grow( xDir, zDir, 8);
        }
    }

    public void growCowardly(List<ServerPlayerEntity> players) {
        if(players.isEmpty()) {
            grow();
        }else{
            ServerPlayerEntity closestPlayer = getClosestPlayer(players, pos);
            boolean xDir=closestPlayer.getX()>=pos.getX();
            boolean yDir=closestPlayer.getY()>=pos.getY();
            boolean zDir=closestPlayer.getZ()>=pos.getZ();
            grow( !xDir, !yDir, !zDir, 3);
        }
    }

    public double getDistanceFromGoo(Position playerPos, BlockPos gooPos){
        return Math.sqrt(gooPos.getSquaredDistance(playerPos, false) );

    }

    public ServerPlayerEntity getClosestPlayer(List<ServerPlayerEntity> players, BlockPos pos){
        ServerPlayerEntity closestPlayer=players.get(0);
        double closestDistance=getDistanceFromGoo(players.get(0).getPos(), pos);
        if(players.size()>1){
            for(int x=1; x<players.size();x++){
                if(getDistanceFromGoo(players.get(x).getPos(), pos)<closestDistance){
                    closestPlayer=players.get(x);
                }
            }
        }
        return closestPlayer;
    }


}
