package net.minecraft.commands.arguments.blocks;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.tags.BlockTags;

public class BlockStateArgument implements ArgumentType<BlockInput>
{
    private static final Collection<String> EXAMPLES = Arrays.asList("stone", "minecraft:stone", "stone[foo=bar]", "foo{bar=baz}");

    public static BlockStateArgument block()
    {
        return new BlockStateArgument();
    }

    public BlockInput parse(StringReader p_116122_) throws CommandSyntaxException
    {
        BlockStateParser blockstateparser = (new BlockStateParser(p_116122_, false)).parse(true);
        return new BlockInput(blockstateparser.getState(), blockstateparser.getProperties().keySet(), blockstateparser.getNbt());
    }

    public static BlockInput getBlock(CommandContext<CommandSourceStack> pContext, String pName)
    {
        return pContext.getArgument(pName, BlockInput.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_116128_, SuggestionsBuilder p_116129_)
    {
        StringReader stringreader = new StringReader(p_116129_.getInput());
        stringreader.setCursor(p_116129_.getStart());
        BlockStateParser blockstateparser = new BlockStateParser(stringreader, false);

        try
        {
            blockstateparser.parse(true);
        }
        catch (CommandSyntaxException commandsyntaxexception)
        {
        }

        return blockstateparser.fillSuggestions(p_116129_, BlockTags.getAllTags());
    }

    public Collection<String> getExamples()
    {
        return EXAMPLES;
    }
}
