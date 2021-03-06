package net.minecraft.world.item.alchemy;

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;

public class Potion
{
    private final String name;
    private final ImmutableList<MobEffectInstance> effects;

    public static Potion byName(String pName)
    {
        return Registry.POTION.get(ResourceLocation.tryParse(pName));
    }

    public Potion(MobEffectInstance... p_43487_)
    {
        this((String)null, p_43487_);
    }

    public Potion(@Nullable String p_43484_, MobEffectInstance... p_43485_)
    {
        this.name = p_43484_;
        this.effects = ImmutableList.copyOf(p_43485_);
    }

    public String getName(String pPrefix)
    {
        return pPrefix + (this.name == null ? Registry.POTION.getKey(this).getPath() : this.name);
    }

    public List<MobEffectInstance> getEffects()
    {
        return this.effects;
    }

    public boolean hasInstantEffects()
    {
        if (!this.effects.isEmpty())
        {
            for (MobEffectInstance mobeffectinstance : this.effects)
            {
                if (mobeffectinstance.getEffect().isInstantenous())
                {
                    return true;
                }
            }
        }

        return false;
    }
}
