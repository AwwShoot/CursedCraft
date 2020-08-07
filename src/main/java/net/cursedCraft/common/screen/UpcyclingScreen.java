package net.cursedCraft.common.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

//This class draws the screen. The Identifier must have the path fully spelled out, rather than just the filename.
public class UpcyclingScreen extends HandledScreen<UpcyclingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("cursedcraft", "textures/gui/container/upcycling_machine.png");
    public UpcyclingScreen(UpcyclingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.drawBackground(matrices, delta, mouseX, mouseY);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        if ((((UpcyclingScreenHandler)this.handler).getSlot(0).hasStack() || ((UpcyclingScreenHandler)this.handler).getSlot(1).hasStack()) && !((UpcyclingScreenHandler)this.handler).getSlot(2).hasStack()) {
            this.drawTexture(matrices, i + 92, j + 31, this.backgroundWidth, 0, 28, 21);
        }
    }
}
