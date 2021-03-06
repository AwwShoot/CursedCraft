package net.cursedCraft.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleMixin {

    //Prints a statement at the construction of the titlescreen.
    @Inject(at = @At("HEAD"), method = "init()V")
    private void piss(CallbackInfo info) {
        System.out.println("You're gonna have a bad time");
    }
}