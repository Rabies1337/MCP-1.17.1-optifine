package net.minecraft.world.level.block.piston;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class PistonStructureResolver
{
    public static final int MAX_PUSH_DEPTH = 12;
    private final Level level;
    private final BlockPos pistonPos;
    private final boolean extending;
    private final BlockPos startPos;
    private final Direction pushDirection;
    private final List<BlockPos> toPush = Lists.newArrayList();
    private final List<BlockPos> toDestroy = Lists.newArrayList();
    private final Direction pistonDirection;

    public PistonStructureResolver(Level p_60418_, BlockPos p_60419_, Direction p_60420_, boolean p_60421_)
    {
        this.level = p_60418_;
        this.pistonPos = p_60419_;
        this.pistonDirection = p_60420_;
        this.extending = p_60421_;

        if (p_60421_)
        {
            this.pushDirection = p_60420_;
            this.startPos = p_60419_.relative(p_60420_);
        }
        else
        {
            this.pushDirection = p_60420_.getOpposite();
            this.startPos = p_60419_.relative(p_60420_, 2);
        }
    }

    public boolean resolve()
    {
        this.toPush.clear();
        this.toDestroy.clear();
        BlockState blockstate = this.level.getBlockState(this.startPos);

        if (!PistonBaseBlock.isPushable(blockstate, this.level, this.startPos, this.pushDirection, false, this.pistonDirection))
        {
            if (this.extending && blockstate.getPistonPushReaction() == PushReaction.DESTROY)
            {
                this.toDestroy.add(this.startPos);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (!this.addBlockLine(this.startPos, this.pushDirection))
        {
            return false;
        }
        else
        {
            for (int i = 0; i < this.toPush.size(); ++i)
            {
                BlockPos blockpos = this.toPush.get(i);

                if (isSticky(this.level.getBlockState(blockpos)) && !this.addBranchingBlocks(blockpos))
                {
                    return false;
                }
            }

            return true;
        }
    }

    private static boolean isSticky(BlockState p_155938_)
    {
        return p_155938_.is(Blocks.SLIME_BLOCK) || p_155938_.is(Blocks.HONEY_BLOCK);
    }

    private static boolean canStickToEachOther(BlockState p_155940_, BlockState p_155941_)
    {
        if (p_155940_.is(Blocks.HONEY_BLOCK) && p_155941_.is(Blocks.SLIME_BLOCK))
        {
            return false;
        }
        else if (p_155940_.is(Blocks.SLIME_BLOCK) && p_155941_.is(Blocks.HONEY_BLOCK))
        {
            return false;
        }
        else
        {
            return isSticky(p_155940_) || isSticky(p_155941_);
        }
    }

    private boolean addBlockLine(BlockPos pOrigin, Direction pFacing)
    {
        BlockState blockstate = this.level.getBlockState(pOrigin);

        if (blockstate.isAir())
        {
            return true;
        }
        else if (!PistonBaseBlock.isPushable(blockstate, this.level, pOrigin, this.pushDirection, false, pFacing))
        {
            return true;
        }
        else if (pOrigin.equals(this.pistonPos))
        {
            return true;
        }
        else if (this.toPush.contains(pOrigin))
        {
            return true;
        }
        else
        {
            int i = 1;

            if (i + this.toPush.size() > 12)
            {
                return false;
            }
            else
            {
                while (isSticky(blockstate))
                {
                    BlockPos blockpos = pOrigin.relative(this.pushDirection.getOpposite(), i);
                    BlockState blockstate1 = blockstate;
                    blockstate = this.level.getBlockState(blockpos);

                    if (blockstate.isAir() || !canStickToEachOther(blockstate1, blockstate) || !PistonBaseBlock.isPushable(blockstate, this.level, blockpos, this.pushDirection, false, this.pushDirection.getOpposite()) || blockpos.equals(this.pistonPos))
                    {
                        break;
                    }

                    ++i;

                    if (i + this.toPush.size() > 12)
                    {
                        return false;
                    }
                }

                int l = 0;

                for (int i1 = i - 1; i1 >= 0; --i1)
                {
                    this.toPush.add(pOrigin.relative(this.pushDirection.getOpposite(), i1));
                    ++l;
                }

                int j1 = 1;

                while (true)
                {
                    BlockPos blockpos1 = pOrigin.relative(this.pushDirection, j1);
                    int j = this.toPush.indexOf(blockpos1);

                    if (j > -1)
                    {
                        this.reorderListAtCollision(l, j);

                        for (int k = 0; k <= j + l; ++k)
                        {
                            BlockPos blockpos2 = this.toPush.get(k);

                            if (isSticky(this.level.getBlockState(blockpos2)) && !this.addBranchingBlocks(blockpos2))
                            {
                                return false;
                            }
                        }

                        return true;
                    }

                    blockstate = this.level.getBlockState(blockpos1);

                    if (blockstate.isAir())
                    {
                        return true;
                    }

                    if (!PistonBaseBlock.isPushable(blockstate, this.level, blockpos1, this.pushDirection, true, this.pushDirection) || blockpos1.equals(this.pistonPos))
                    {
                        return false;
                    }

                    if (blockstate.getPistonPushReaction() == PushReaction.DESTROY)
                    {
                        this.toDestroy.add(blockpos1);
                        return true;
                    }

                    if (this.toPush.size() >= 12)
                    {
                        return false;
                    }

                    this.toPush.add(blockpos1);
                    ++l;
                    ++j1;
                }
            }
        }
    }

    private void reorderListAtCollision(int pOffsets, int pIndex)
    {
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> list1 = Lists.newArrayList();
        List<BlockPos> list2 = Lists.newArrayList();
        list.addAll(this.toPush.subList(0, pIndex));
        list1.addAll(this.toPush.subList(this.toPush.size() - pOffsets, this.toPush.size()));
        list2.addAll(this.toPush.subList(pIndex, this.toPush.size() - pOffsets));
        this.toPush.clear();
        this.toPush.addAll(list);
        this.toPush.addAll(list1);
        this.toPush.addAll(list2);
    }

    private boolean addBranchingBlocks(BlockPos pFromPos)
    {
        BlockState blockstate = this.level.getBlockState(pFromPos);

        for (Direction direction : Direction.values())
        {
            if (direction.getAxis() != this.pushDirection.getAxis())
            {
                BlockPos blockpos = pFromPos.relative(direction);
                BlockState blockstate1 = this.level.getBlockState(blockpos);

                if (canStickToEachOther(blockstate1, blockstate) && !this.addBlockLine(blockpos, direction))
                {
                    return false;
                }
            }
        }

        return true;
    }

    public Direction getPushDirection()
    {
        return this.pushDirection;
    }

    public List<BlockPos> getToPush()
    {
        return this.toPush;
    }

    public List<BlockPos> getToDestroy()
    {
        return this.toDestroy;
    }
}
