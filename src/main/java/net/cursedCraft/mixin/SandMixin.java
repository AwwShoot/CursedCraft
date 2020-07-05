package net.cursedCraft.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FallingBlock.class)
public class SandMixin {
    @Inject(at=@At("RETURN"), method="canFallThrough", cancellable = true)
    private static void drop( BlockState state, CallbackInfoReturnable<Boolean> info){
        info.setReturnValue(true);

    }

    @Inject(at=@At("RETURN"), method="getFallDelay", cancellable = true)
    protected void slowDownPlease(CallbackInfoReturnable<Integer> info){info.setReturnValue((int)(Math.random()*11+10));};



}
