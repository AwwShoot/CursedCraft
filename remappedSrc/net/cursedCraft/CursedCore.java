
package net.cursedCraft;

import net.cursedCraft.client.CursedClientInitializer;
import net.cursedCraft.common.CursedRegistry;
import net.fabricmc.api.ModInitializer;


public class CursedCore implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        System.out.println("initializiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiing");

        CursedRegistry.register();
        CursedClientInitializer.onInitializeClient();

    }


}