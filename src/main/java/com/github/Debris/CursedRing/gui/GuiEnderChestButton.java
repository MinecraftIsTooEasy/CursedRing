package com.github.Debris.CursedRing.gui;

import com.github.Debris.CursedRing.CursedRing;
import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.github.Debris.CursedRing.network.C2SOpenEnderChest;
import com.github.Debris.CursedRing.util.PlayerUtil;
import fi.dy.masa.malilib.gui.DrawContext;
import fi.dy.masa.malilib.render.RenderUtils;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.GuiButton;
import net.minecraft.GuiContainer;
import net.minecraft.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiEnderChestButton extends GuiButton {

    public GuiEnderChestButton(int x, int y) {
        super(CursedRing.ButtonID, x, y, 20, 18, "");
        this.enabled = CursedRingConfig.EnderChest.getBooleanValue();
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (this.drawButton && enabled) {
            Network.sendToServer(new C2SOpenEnderChest());
        }
        return super.mousePressed(mc, mouseX, mouseY);
    }

    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        this.drawButton = PlayerUtil.isCursedRingWorn(par1Minecraft.thePlayer) && CursedRingConfig.EnderChest.getBooleanValue();
        if (this.drawButton && this.enabled) {
            RenderUtils.bindTexture(CursedRing.EnderChest);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            this.mouseDragged(par1Minecraft, par2, par3);
            int u = 0;
            int v = this.field_82253_i ? 19 : 0;
            RenderUtils.drawTexturedRect(this.xPosition, this.yPosition, u, v, 20, 18);
            if (this.field_82253_i) {
                RenderUtils.drawCreativeTabHoveringText
                        ("按下" + CursedRingConfig.OpenEnderChest.getDisplayText() + "以打开末影箱",
                                par2,
                                par3, new DrawContext());
            }
        }
    }
}
