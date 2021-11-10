package net.minecraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public abstract class AgeableListModel<E extends Entity> extends EntityModel<E>
{
    private final boolean scaleHead;
    private final float babyYHeadOffset;
    private final float babyZHeadOffset;
    private final float babyHeadScale;
    private final float babyBodyScale;
    private final float bodyYOffset;

    protected AgeableListModel(boolean p_102023_, float p_102024_, float p_102025_)
    {
        this(p_102023_, p_102024_, p_102025_, 2.0F, 2.0F, 24.0F);
    }

    protected AgeableListModel(boolean p_102027_, float p_102028_, float p_102029_, float p_102030_, float p_102031_, float p_102032_)
    {
        this(RenderType::entityCutoutNoCull, p_102027_, p_102028_, p_102029_, p_102030_, p_102031_, p_102032_);
    }

    protected AgeableListModel(Function<ResourceLocation, RenderType> p_102015_, boolean p_102016_, float p_102017_, float p_102018_, float p_102019_, float p_102020_, float p_102021_)
    {
        super(p_102015_);
        this.scaleHead = p_102016_;
        this.babyYHeadOffset = p_102017_;
        this.babyZHeadOffset = p_102018_;
        this.babyHeadScale = p_102019_;
        this.babyBodyScale = p_102020_;
        this.bodyYOffset = p_102021_;
    }

    protected AgeableListModel()
    {
        this(false, 5.0F, 2.0F);
    }

    public void renderToBuffer(PoseStack pMatrixStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha)
    {
        if (this.young)
        {
            pMatrixStack.pushPose();

            if (this.scaleHead)
            {
                float f = 1.5F / this.babyHeadScale;
                pMatrixStack.scale(f, f, f);
            }

            pMatrixStack.translate(0.0D, (double)(this.babyYHeadOffset / 16.0F), (double)(this.babyZHeadOffset / 16.0F));
            this.headParts().forEach((p_102081_) ->
            {
                p_102081_.render(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
            pMatrixStack.popPose();
            pMatrixStack.pushPose();
            float f1 = 1.0F / this.babyBodyScale;
            pMatrixStack.scale(f1, f1, f1);
            pMatrixStack.translate(0.0D, (double)(this.bodyYOffset / 16.0F), 0.0D);
            this.bodyParts().forEach((p_102071_) ->
            {
                p_102071_.render(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
            pMatrixStack.popPose();
        }
        else
        {
            this.headParts().forEach((p_102061_) ->
            {
                p_102061_.render(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
            this.bodyParts().forEach((p_102051_) ->
            {
                p_102051_.render(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
            });
        }
    }

    protected abstract Iterable<ModelPart> headParts();

    protected abstract Iterable<ModelPart> bodyParts();
}