package net.cursedCraft.mixin;

import net.cursedCraft.common.CursedRegistry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(at=@At("RETURN"), method="addForestTrees", cancellable = true)
    private static void generateTreeStand(GenerationSettings.Builder builder, CallbackInfo Info){
        builder.structureFeature(CursedRegistry.TREESTAND_CONFIGURED);

    }
}
