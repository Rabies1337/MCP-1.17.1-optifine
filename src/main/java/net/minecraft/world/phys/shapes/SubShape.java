package net.minecraft.world.phys.shapes;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

public final class SubShape extends DiscreteVoxelShape
{
    private final DiscreteVoxelShape parent;
    private final int startX;
    private final int startY;
    private final int startZ;
    private final int endX;
    private final int endY;
    private final int endZ;

    protected SubShape(DiscreteVoxelShape p_83190_, int p_83191_, int p_83192_, int p_83193_, int p_83194_, int p_83195_, int p_83196_)
    {
        super(p_83194_ - p_83191_, p_83195_ - p_83192_, p_83196_ - p_83193_);
        this.parent = p_83190_;
        this.startX = p_83191_;
        this.startY = p_83192_;
        this.startZ = p_83193_;
        this.endX = p_83194_;
        this.endY = p_83195_;
        this.endZ = p_83196_;
    }

    public boolean isFull(int pX, int pY, int pZ)
    {
        return this.parent.isFull(this.startX + pX, this.startY + pY, this.startZ + pZ);
    }

    public void fill(int p_166060_, int p_166061_, int p_166062_)
    {
        this.parent.fill(this.startX + p_166060_, this.startY + p_166061_, this.startZ + p_166062_);
    }

    public int firstFull(Direction.Axis pAxis)
    {
        return this.clampToShape(pAxis, this.parent.firstFull(pAxis));
    }

    public int lastFull(Direction.Axis pAxis)
    {
        return this.clampToShape(pAxis, this.parent.lastFull(pAxis));
    }

    private int clampToShape(Direction.Axis p_166057_, int p_166058_)
    {
        int i = p_166057_.choose(this.startX, this.startY, this.startZ);
        int j = p_166057_.choose(this.endX, this.endY, this.endZ);
        return Mth.clamp(p_166058_, i, j) - i;
    }
}
