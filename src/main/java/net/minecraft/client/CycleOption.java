package net.minecraft.client;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

public class CycleOption<T> extends Option
{
    public CycleOption.OptionSetter<T> setter;
    public Function<Options, T> getter;
    public Supplier<CycleButton.Builder<T>> buttonSetup;
    private Function<Minecraft, CycleButton.TooltipSupplier<T>> tooltip = (mcIn) ->
    {
        return (p_313636_0_) -> {
            return ImmutableList.of();
        };
    };

    protected CycleOption(String p_167717_, Function<Options, T> p_167718_, CycleOption.OptionSetter<T> p_167719_, Supplier<CycleButton.Builder<T>> p_167720_)
    {
        super(p_167717_);
        this.getter = p_167718_;
        this.setter = p_167719_;
        this.buttonSetup = p_167720_;
    }

    public static <T> CycleOption<T> create(String p_167738_, List<T> p_167739_, Function<T, Component> p_167740_, Function<Options, T> p_167741_, CycleOption.OptionSetter<T> p_167742_)
    {
        return new CycleOption<>(p_167738_, p_167741_, p_167742_, () ->
        {
            return CycleButton.builder(p_167740_).withValues(p_167739_);
        });
    }

    public static <T> CycleOption<T> create(String p_167748_, Supplier<List<T>> p_167749_, Function<T, Component> p_167750_, Function<Options, T> p_167751_, CycleOption.OptionSetter<T> p_167752_)
    {
        return new CycleOption<>(p_167748_, p_167751_, p_167752_, () ->
        {
            return CycleButton.builder(p_167750_).withValues(p_167749_.get());
        });
    }

    public static <T> CycleOption<T> create(String p_167730_, List<T> p_167731_, List<T> p_167732_, BooleanSupplier p_167733_, Function<T, Component> p_167734_, Function<Options, T> p_167735_, CycleOption.OptionSetter<T> p_167736_)
    {
        return new CycleOption<>(p_167730_, p_167735_, p_167736_, () ->
        {
            return CycleButton.builder(p_167734_).withValues(p_167733_, p_167731_, p_167732_);
        });
    }

    public static <T> CycleOption<T> m_167764_(String p_167765_, T[] p_167766_, Function<T, Component> p_167767_, Function<Options, T> p_167768_, CycleOption.OptionSetter<T> p_167769_)
    {
        return new CycleOption<>(p_167765_, p_167768_, p_167769_, () ->
        {
            return CycleButton.builder(p_167767_).m_168961_(p_167766_);
        });
    }

    public static CycleOption<Boolean> createBinaryOption(String p_167759_, Component p_167760_, Component p_167761_, Function<Options, Boolean> p_167762_, CycleOption.OptionSetter<Boolean> p_167763_)
    {
        return new CycleOption<>(p_167759_, p_167762_, p_167763_, () ->
        {
            return CycleButton.booleanBuilder(p_167760_, p_167761_);
        });
    }

    public static CycleOption<Boolean> createOnOff(String p_167744_, Function<Options, Boolean> p_167745_, CycleOption.OptionSetter<Boolean> p_167746_)
    {
        return new CycleOption<>(p_167744_, p_167745_, p_167746_, CycleButton::onOffBuilder);
    }

    public static CycleOption<Boolean> createOnOff(String p_167754_, Component p_167755_, Function<Options, Boolean> p_167756_, CycleOption.OptionSetter<Boolean> p_167757_)
    {
        return createOnOff(p_167754_, p_167756_, p_167757_).setTooltip((p_193144_1_) ->
        {
            List<FormattedCharSequence> list = p_193144_1_.font.split(p_167755_, 200);
            return (p_313644_1_) -> {
                return list;
            };
        });
    }

    public CycleOption<T> setTooltip(Function<Minecraft, CycleButton.TooltipSupplier<T>> p_167774_)
    {
        this.tooltip = p_167774_;
        return this;
    }

    public AbstractWidget createButton(Options pOptions, int pX, int pY, int pWidth)
    {
        CycleButton.TooltipSupplier<T> tooltipsupplier = this.tooltip.apply(Minecraft.getInstance());
        return this.buttonSetup.get().withTooltip(tooltipsupplier).withInitialValue(this.getter.apply(pOptions)).create(pX, pY, pWidth, 20, this.getCaption(), (p_193087_2_, p_193087_3_) ->
        {
            this.setter.accept(pOptions, this, p_193087_3_);
            pOptions.save();
        });
    }

    @FunctionalInterface
    public interface OptionSetter<T>
    {
        void accept(Options p_167796_, Option p_167797_, T p_167798_);
    }
}
