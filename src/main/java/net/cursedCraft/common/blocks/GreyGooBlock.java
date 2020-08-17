package net.cursedCraft.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class GreyGooBlock extends GooBlockBase {
    public final boolean randomTicks =true;
    
    public GreyGooBlock(Settings settings) {
        super(settings.ticksRandomly().strength(0.5F));
    }

    public boolean hasRandomTicks(BlockState state) {
        return this.randomTicks;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random){
        //instantiating the fields here to make interface methods a little cleaner.
        this.random= world.random;
        this.world=world;
        this.pos=pos;

        if(!grow())
            world.removeBlock(pos, false);
    }




}
