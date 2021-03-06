package net.minecraft.client.gui.screens.inventory.tooltip;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public interface ClientTooltipComponent
{
    static ClientTooltipComponent create(FormattedCharSequence p_169949_)
    {
        return new ClientTextTooltip(p_169949_);
    }

    static ClientTooltipComponent create(TooltipComponent p_169951_)
    {
        if (p_169951_ instanceof BundleTooltip)
        {
            return new ClientBundleTooltip((BundleTooltip)p_169951_);
        }
        else
        {
            throw new IllegalArgumentException("Unknown TooltipComponent");
        }
    }

    int getHeight();

    int getWidth(Font p_169952_);

default void renderText(Font p_169953_, int p_169954_, int p_169955_, Matrix4f p_169956_, MultiBufferSource.BufferSource p_169957_)
    {
    }

default void renderImage(Font p_169958_, int p_169959_, int p_169960_, PoseStack p_169961_, ItemRenderer p_169962_, int p_169963_, TextureManager p_169964_)
    {
    }
}
