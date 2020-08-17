package net.cursedCraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class GooBlockBase extends Block {

    public GooBlockBase(Settings settings) {
        super(settings);
    }


    /**
     *
     * @param target BlockPos of the block that's being tested
     * @return true if the BlockState at that BlockPos is something goo can spread to by default
     */
    public boolean canSpreadTo(BlockPos target){
        BlockState targetBlock= world.getBlockState(target);
        return (!targetBlock.isAir()&&!targetBlock.isOf(Blocks.FIRE)&&!targetBlock.equals(getDefaultState()));
    }
    //compatability dummy values to be filled in by the actual class.

    Random random;
    BlockPos pos;
    ServerWorld world;

    /**
     *
     * @param xDir Whether to spread in the positive X direction or negative X direction.
     * @param zDir Whether to spread in the positive Z direction or negative Z direction.
     * @param range How far from the block to search for a target.
     * @return true if the goo successfully spreads to another block
     */
    public boolean grow(boolean xDir, boolean zDir, int range){
        for(int x=0; x<8; x++){
            int randX=random.nextInt(range);
            int randZ=random.nextInt(range);
            BlockPos target=pos.add(xDir ? randX : -randX, random.nextInt(3)-1, zDir ? randZ : -randZ);
            if(target.getY()<0)
                target.add(0,-target.getY(),0);
            if(canSpreadTo(target)) {

                world.setBlockState(target, this.getDefaultState());
                return true;
            }
        }
        return false;
    }

    /**
     * Default method. Attempts to spread to any block within 1 block of the origin.
     * @return true if the goo successfully spreads to another block
     */
    public boolean grow(){
        for(int x=0; x<8; x++){




            //adding -1, 0, or 1 to the original position to try to spread to something within one block.
            BlockPos target = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
            //makes sure it doesn't try to spread into the void.
            if(target.getY()<0)
                target.add(0,-target.getY(),0);
            if(canSpreadTo(target)) {

                world.setBlockState(target, this.getDefaultState());
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param xDir Whether to spread in the positive X direction or negative X direction.
     * @param yDir Whether to spread in the positive y direction or negative y direction.
     * @param zDir Whether to spread in the positive Z direction or negative Z direction.
     * @param range How far from the block to search for a target.
     * @return true if the goo successfully spreads to another block
     */
    public boolean grow(boolean xDir, boolean yDir, boolean zDir, int range){
        for(int a=0; a<8; a++){
            int randX=random.nextInt(range);
            int randY=random.nextInt(range);
            int randZ=random.nextInt(range);

            BlockPos target=pos.add(xDir ? randX : -randX, yDir ? randY : -randY, zDir ? randZ : -randZ);
            if(target.getY()<0)
                target.add(0,-target.getY(),0);
            if((canSpreadTo(target)|| range==2)&& !world.getBlockState(target).equals(this.getDefaultState())) {

                world.setBlockState(target, this.getDefaultState());
                return true;
            }
        }
        return false;
    }
}
