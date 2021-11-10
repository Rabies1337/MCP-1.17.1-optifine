package net.minecraft.world.phys.shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;

public interface CollisionContext
{
    static CollisionContext empty()
    {
        return EntityCollisionContext.EMPTY;
    }

    static CollisionContext of(Entity pEntity)
    {
        return new EntityCollisionContext(pEntity);
    }

    boolean isDescending();

    boolean isAbove(VoxelShape p_82755_, BlockPos p_82756_, boolean p_82757_);

    boolean hasItemOnFeet(Item p_165990_);

    boolean isHoldingItem(Item pItem);

    boolean canStandOnFluid(FluidState p_82753_, FlowingFluid p_82754_);
}
