package net.cursedCraft.client;

import net.cursedCraft.common.CursedRegistry;
import net.cursedCraft.common.screen.UpcyclingScreen;
import net.cursedCraft.common.screen.UpcyclingScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

import static net.cursedCraft.common.CursedRegistry.getID;
@Environment(EnvType.CLIENT)
public class CursedClientInitializer {



    public static void onInitializeClient() {
        ScreenRegistry.<UpcyclingScreenHandler, UpcyclingScreen>register(CursedRegistry.UPCYCLING_SCREEN_HANDLER, (type, inventory, title) -> new UpcyclingScreen(type, inventory, title));
    }
}
