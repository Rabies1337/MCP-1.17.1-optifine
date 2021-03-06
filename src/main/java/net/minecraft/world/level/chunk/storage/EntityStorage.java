package net.minecraft.world.level.chunk.storage;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DataFixer;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.util.thread.ProcessorMailbox;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.entity.ChunkEntities;
import net.minecraft.world.level.entity.EntityPersistentStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityStorage implements EntityPersistentStorage<Entity>
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ENTITIES_TAG = "Entities";
    private static final String POSITION_TAG = "Position";
    private final ServerLevel level;
    private final IOWorker worker;
    private final LongSet emptyChunks = new LongOpenHashSet();
    private final ProcessorMailbox<Runnable> f_182485_;
    protected final DataFixer fixerUpper;

    public EntityStorage(ServerLevel pLevel, File pFolder, DataFixer pFixerUpper, boolean pSync, Executor pMainThreadExecutor)
    {
        this.level = pLevel;
        this.fixerUpper = pFixerUpper;
        this.f_182485_ = ProcessorMailbox.create(pMainThreadExecutor, "entity-deserializer");
        this.worker = new IOWorker(pFolder, pSync, "entities");
    }

    public CompletableFuture<ChunkEntities<Entity>> loadEntities(ChunkPos p_156551_)
    {
        return this.emptyChunks.contains(p_156551_.toLong()) ? CompletableFuture.completedFuture(emptyChunk(p_156551_)) : this.worker.loadAsync(p_156551_).thenApplyAsync((p_156557_) ->
        {
            if (p_156557_ == null)
            {
                this.emptyChunks.add(p_156551_.toLong());
                return emptyChunk(p_156551_);
            }
            else {
                try {
                    ChunkPos chunkpos = readChunkPos(p_156557_);

                    if (!Objects.equals(p_156551_, chunkpos))
                    {
                        LOGGER.error("Chunk file at {} is in the wrong location. (Expected {}, got {})", p_156551_, p_156551_, chunkpos);
                    }
                }
                catch (Exception exception)
                {
                    LOGGER.warn("Failed to parse chunk {} position info", p_156551_, exception);
                }

                CompoundTag compoundtag = this.upgradeChunkTag(p_156557_);
                ListTag listtag = compoundtag.getList("Entities", 10);
                List<Entity> list = EntityType.loadEntitiesRecursive(listtag, this.level).collect(ImmutableList.toImmutableList());
                return new ChunkEntities<>(p_156551_, list);
            }
        }, this.f_182485_::tell);
    }

    private static ChunkPos readChunkPos(CompoundTag p_156571_)
    {
        int[] aint = p_156571_.getIntArray("Position");
        return new ChunkPos(aint[0], aint[1]);
    }

    private static void writeChunkPos(CompoundTag p_156563_, ChunkPos p_156564_)
    {
        p_156563_.put("Position", new IntArrayTag(new int[] {p_156564_.x, p_156564_.z}));
    }

    private static ChunkEntities<Entity> emptyChunk(ChunkPos p_156569_)
    {
        return new ChunkEntities<>(p_156569_, ImmutableList.of());
    }

    public void storeEntities(ChunkEntities<Entity> p_156559_)
    {
        ChunkPos chunkpos = p_156559_.getPos();

        if (p_156559_.isEmpty())
        {
            if (this.emptyChunks.add(chunkpos.toLong()))
            {
                this.worker.store(chunkpos, (CompoundTag)null);
            }
        }
        else
        {
            ListTag listtag = new ListTag();
            p_156559_.getEntities().forEach((p_156567_) ->
            {
                CompoundTag compoundtag1 = new CompoundTag();

                if (p_156567_.save(compoundtag1))
                {
                    listtag.add(compoundtag1);
                }
            });
            CompoundTag compoundtag = new CompoundTag();
            compoundtag.putInt("DataVersion", SharedConstants.getCurrentVersion().getWorldVersion());
            compoundtag.put("Entities", listtag);
            writeChunkPos(compoundtag, chunkpos);
            this.worker.store(chunkpos, compoundtag).exceptionally((p_156554_) ->
            {
                LOGGER.error("Failed to store chunk {}", chunkpos, p_156554_);
                return null;
            });
            this.emptyChunks.remove(chunkpos.toLong());
        }
    }

    public void m_182219_(boolean p_182487_)
    {
        this.worker.m_182498_(p_182487_).join();
        this.f_182485_.m_182329_();
    }

    private CompoundTag upgradeChunkTag(CompoundTag p_156573_)
    {
        int i = getVersion(p_156573_);
        return NbtUtils.update(this.fixerUpper, DataFixTypes.ENTITY_CHUNK, p_156573_, i);
    }

    public static int getVersion(CompoundTag p_156561_)
    {
        return p_156561_.contains("DataVersion", 99) ? p_156561_.getInt("DataVersion") : -1;
    }

    public void close() throws IOException
    {
        this.worker.close();
    }
}
