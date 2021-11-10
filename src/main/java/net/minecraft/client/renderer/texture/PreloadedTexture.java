package net.minecraft.client.renderer.texture;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

public class PreloadedTexture extends SimpleTexture
{
    @Nullable
    private CompletableFuture<SimpleTexture.TextureImage> future;

    public PreloadedTexture(ResourceManager p_118102_, ResourceLocation p_118103_, Executor p_118104_)
    {
        super(p_118103_);
        this.future = CompletableFuture.supplyAsync(() ->
        {
            return SimpleTexture.TextureImage.load(p_118102_, p_118103_);
        }, p_118104_);
    }

    protected SimpleTexture.TextureImage getTextureImage(ResourceManager pResourceManager)
    {
        if (this.future != null)
        {
            SimpleTexture.TextureImage simpletexture$textureimage = this.future.join();
            this.future = null;
            return simpletexture$textureimage;
        }
        else
        {
            return SimpleTexture.TextureImage.load(pResourceManager, this.location);
        }
    }

    public CompletableFuture<Void> getFuture()
    {
        return this.future == null ? CompletableFuture.completedFuture((Void)null) : this.future.thenApply((p_118110_) ->
        {
            return null;
        });
    }

    public void reset(TextureManager pTextureManager, ResourceManager pResourceManager, ResourceLocation pResourceLocation, Executor pExecutor)
    {
        this.future = CompletableFuture.supplyAsync(() ->
        {
            return SimpleTexture.TextureImage.load(pResourceManager, this.location);
        }, Util.backgroundExecutor());
        this.future.thenRunAsync(() ->
        {
            pTextureManager.register(this.location, this);
        }, executor(pExecutor));
    }

    private static Executor executor(Executor pExecutor)
    {
        return (p_118124_) ->
        {
            pExecutor.execute(() -> {
                RenderSystem.recordRenderCall(p_118124_::run);
            });
        };
    }
}
