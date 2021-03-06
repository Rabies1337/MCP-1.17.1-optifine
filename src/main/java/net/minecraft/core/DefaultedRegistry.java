package net.minecraft.core;

import com.mojang.serialization.Lifecycle;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class DefaultedRegistry<T> extends MappedRegistry<T>
{
    private final ResourceLocation defaultKey;
    private T defaultValue;

    public DefaultedRegistry(String p_122312_, ResourceKey <? extends Registry<T >> p_122313_, Lifecycle p_122314_)
    {
        super(p_122313_, p_122314_);
        this.defaultKey = new ResourceLocation(p_122312_);
    }

    public <V extends T> V registerMapping(int pId, ResourceKey<T> pName, V pInstance, Lifecycle pLifecycle)
    {
        if (this.defaultKey.equals(pName.location()))
        {
            this.defaultValue = (T)pInstance;
        }

        return super.registerMapping(pId, pName, pInstance, pLifecycle);
    }

    public int getId(@Nullable T pValue)
    {
        int i = super.getId(pValue);
        return i == -1 ? super.getId(this.defaultValue) : i;
    }

    @Nonnull
    public ResourceLocation getKey(T pValue)
    {
        ResourceLocation resourcelocation = super.getKey(pValue);
        return resourcelocation == null ? this.defaultKey : resourcelocation;
    }

    @Nonnull
    public T get(@Nullable ResourceLocation pName)
    {
        T t = super.get(pName);
        return (T)(t == null ? this.defaultValue : t);
    }

    public Optional<T> getOptional(@Nullable ResourceLocation pId)
    {
        return Optional.ofNullable(super.get(pId));
    }

    @Nonnull
    public T byId(int pValue)
    {
        T t = super.byId(pValue);
        return (T)(t == null ? this.defaultValue : t);
    }

    @Nonnull
    public T getRandom(Random p_122326_)
    {
        T t = super.getRandom(p_122326_);
        return (T)(t == null ? this.defaultValue : t);
    }

    public ResourceLocation getDefaultKey()
    {
        return this.defaultKey;
    }
}
