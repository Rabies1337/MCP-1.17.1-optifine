package net.minecraft.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SalmonModel<T extends Entity> extends HierarchicalModel<T>
{
    private static final String BODY_FRONT = "body_front";
    private static final String BODY_BACK = "body_back";
    private final ModelPart root;
    private final ModelPart bodyBack;

    public SalmonModel(ModelPart p_170896_)
    {
        this.root = p_170896_;
        this.bodyBack = p_170896_.getChilds("body_back");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        int i = 20;
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F), PartPose.offset(0.0F, 20.0F, 0.0F));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F), PartPose.offset(0.0F, 20.0F, 8.0F));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F), PartPose.offset(0.0F, 20.0F, 0.0F));
        partdefinition2.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F), PartPose.offset(0.0F, 0.0F, 8.0F));
        partdefinition1.addOrReplaceChild("top_front_fin", CubeListBuilder.create().texOffs(2, 1).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 3.0F), PartPose.offset(0.0F, -4.5F, 5.0F));
        partdefinition2.addOrReplaceChild("top_back_fin", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F), PartPose.offset(0.0F, -4.5F, -1.0F));
        partdefinition.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(-4, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F), PartPose.offsetAndRotation(-1.5F, 21.5F, 0.0F, 0.0F, 0.0F, (-(float)Math.PI / 4F)));
        partdefinition.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F), PartPose.offsetAndRotation(1.5F, 21.5F, 0.0F, 0.0F, 0.0F, ((float)Math.PI / 4F)));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public ModelPart root()
    {
        return this.root;
    }

    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
    {
        float f = 1.0F;
        float f1 = 1.0F;

        if (!pEntity.isInWater())
        {
            f = 1.3F;
            f1 = 1.7F;
        }

        this.bodyBack.yRot = -f * 0.25F * Mth.sin(f1 * 0.6F * pAgeInTicks);
    }
}
