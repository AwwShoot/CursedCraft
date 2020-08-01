package net.cursedCraft.mixin;

import net.cursedCraft.common.screen.UpcyclingScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScreenHandlerType.class)
public abstract class ScreenHandlerTypeMixin implements ScreenHandlerTypeInterface{

    protected ScreenHandlerTypeMixin(ScreenHandlerType<?> type, int syncId) {
        super();
    }

   /* public ScreenHandlerType factoryFactory(){
        return new ScreenHandlerType(UpcyclingScreenHandler::new );
    };
    */

}
