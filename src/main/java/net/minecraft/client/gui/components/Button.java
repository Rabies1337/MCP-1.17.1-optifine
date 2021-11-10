package net.minecraft.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Consumer;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class Button extends AbstractButton
{
    public static final Button.OnTooltip NO_TOOLTIP = (p_93740_, p_93741_, p_93742_, p_93743_) ->
    {
    };
    protected final Button.OnPress onPress;
    protected final Button.OnTooltip onTooltip;

    public Button(int p_93721_, int p_93722_, int p_93723_, int p_93724_, Component p_93725_, Button.OnPress p_93726_)
    {
        this(p_93721_, p_93722_, p_93723_, p_93724_, p_93725_, p_93726_, NO_TOOLTIP);
    }

    public Button(int p_93728_, int p_93729_, int p_93730_, int p_93731_, Component p_93732_, Button.OnPress p_93733_, Button.OnTooltip p_93734_)
    {
        super(p_93728_, p_93729_, p_93730_, p_93731_, p_93732_);
        this.onPress = p_93733_;
        this.onTooltip = p_93734_;
    }

    public void onPress()
    {
        this.onPress.onPress(this);
    }

    public void renderButton(PoseStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks)
    {
        super.renderButton(pMatrixStack, pMouseX, pMouseY, pPartialTicks);

        if (this.isHovered())
        {
            this.renderToolTip(pMatrixStack, pMouseX, pMouseY);
        }
    }

    public void renderToolTip(PoseStack pMatrixStack, int pMouseX, int pMouseY)
    {
        this.onTooltip.onTooltip(this, pMatrixStack, pMouseX, pMouseY);
    }

    public void updateNarration(NarrationElementOutput p_168838_)
    {
        this.defaultButtonNarrationText(p_168838_);
        this.onTooltip.narrateTooltip((p_168841_) ->
        {
            p_168838_.add(NarratedElementType.HINT, p_168841_);
        });
    }

    public interface OnPress
    {
        void onPress(Button p_93751_);
    }

    public interface OnTooltip
    {
        void onTooltip(Button p_93753_, PoseStack p_93754_, int p_93755_, int p_93756_);

    default void narrateTooltip(Consumer<Component> p_168842_)
        {
        }
    }
}
