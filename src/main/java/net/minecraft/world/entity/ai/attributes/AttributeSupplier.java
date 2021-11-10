package net.minecraft.world.entity.ai.attributes;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.core.Registry;

public class AttributeSupplier
{
    private final Map<Attribute, AttributeInstance> instances;

    public AttributeSupplier(Map<Attribute, AttributeInstance> p_22243_)
    {
        this.instances = ImmutableMap.copyOf(p_22243_);
    }

    private AttributeInstance getAttributeInstance(Attribute pAttribute)
    {
        AttributeInstance attributeinstance = this.instances.get(pAttribute);

        if (attributeinstance == null)
        {
            throw new IllegalArgumentException("Can't find attribute " + Registry.ATTRIBUTE.getKey(pAttribute));
        }
        else
        {
            return attributeinstance;
        }
    }

    public double getValue(Attribute pAttribute)
    {
        return this.getAttributeInstance(pAttribute).getValue();
    }

    public double getBaseValue(Attribute pAttribute)
    {
        return this.getAttributeInstance(pAttribute).getBaseValue();
    }

    public double getModifierValue(Attribute pAttribute, UUID pId)
    {
        AttributeModifier attributemodifier = this.getAttributeInstance(pAttribute).getModifier(pId);

        if (attributemodifier == null)
        {
            throw new IllegalArgumentException("Can't find modifier " + pId + " on attribute " + Registry.ATTRIBUTE.getKey(pAttribute));
        }
        else
        {
            return attributemodifier.getAmount();
        }
    }

    @Nullable
    public AttributeInstance createInstance(Consumer<AttributeInstance> pOnChangedCallback, Attribute pAttribute)
    {
        AttributeInstance attributeinstance = this.instances.get(pAttribute);

        if (attributeinstance == null)
        {
            return null;
        }
        else
        {
            AttributeInstance attributeinstance1 = new AttributeInstance(pAttribute, pOnChangedCallback);
            attributeinstance1.replaceFrom(attributeinstance);
            return attributeinstance1;
        }
    }

    public static AttributeSupplier.Builder builder()
    {
        return new AttributeSupplier.Builder();
    }

    public boolean hasAttribute(Attribute pAttribute)
    {
        return this.instances.containsKey(pAttribute);
    }

    public boolean hasModifier(Attribute pAttribute, UUID pId)
    {
        AttributeInstance attributeinstance = this.instances.get(pAttribute);
        return attributeinstance != null && attributeinstance.getModifier(pId) != null;
    }

    public static class Builder
    {
        private final Map<Attribute, AttributeInstance> builder = Maps.newHashMap();
        private boolean instanceFrozen;

        private AttributeInstance create(Attribute pAttribute)
        {
            AttributeInstance attributeinstance = new AttributeInstance(pAttribute, (p_22273_) ->
            {
                if (this.instanceFrozen)
                {
                    throw new UnsupportedOperationException("Tried to change value for default attribute instance: " + Registry.ATTRIBUTE.getKey(pAttribute));
                }
            });
            this.builder.put(pAttribute, attributeinstance);
            return attributeinstance;
        }

        public AttributeSupplier.Builder add(Attribute pAttribute)
        {
            this.create(pAttribute);
            return this;
        }

        public AttributeSupplier.Builder add(Attribute pAttribute, double p_22270_)
        {
            AttributeInstance attributeinstance = this.create(pAttribute);
            attributeinstance.setBaseValue(p_22270_);
            return this;
        }

        public AttributeSupplier build()
        {
            this.instanceFrozen = true;
            return new AttributeSupplier(this.builder);
        }
    }
}
