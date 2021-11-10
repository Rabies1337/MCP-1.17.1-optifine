package net.minecraft.client.particle;

import java.util.Optional;
import java.util.Random;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class SuspendedParticle extends TextureSheetParticle
{
    SuspendedParticle(ClientLevel p_172403_, SpriteSet p_172404_, double p_172405_, double p_172406_, double p_172407_)
    {
        super(p_172403_, p_172405_, p_172406_ - 0.125D, p_172407_);
        this.setSize(0.01F, 0.01F);
        this.pickSprite(p_172404_);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.2F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    SuspendedParticle(ClientLevel p_172409_, SpriteSet p_172410_, double p_172411_, double p_172412_, double p_172413_, double p_172414_, double p_172415_, double p_172416_)
    {
        super(p_172409_, p_172411_, p_172412_ - 0.125D, p_172413_, p_172414_, p_172415_, p_172416_);
        this.setSize(0.01F, 0.01F);
        this.pickSprite(p_172410_);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    public ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class CrimsonSporeProvider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public CrimsonSporeProvider(SpriteSet p_108042_)
        {
            this.sprite = p_108042_;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double p_108056_, double pY, double p_108058_, double pZ, double p_108060_)
        {
            Random random = pLevel.random;
            double d0 = random.nextGaussian() * (double)1.0E-6F;
            double d1 = random.nextGaussian() * (double)1.0E-4F;
            double d2 = random.nextGaussian() * (double)1.0E-6F;
            SuspendedParticle suspendedparticle = new SuspendedParticle(pLevel, this.sprite, pX, p_108056_, pY, d0, d1, d2);
            suspendedparticle.setColor(0.9F, 0.4F, 0.5F);
            return suspendedparticle;
        }
    }

    public static class SporeBlossomAirProvider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public SporeBlossomAirProvider(SpriteSet p_172419_)
        {
            this.sprite = p_172419_;
        }

        public Particle createParticle(SimpleParticleType p_172430_, ClientLevel p_172431_, double p_172432_, double p_172433_, double p_172434_, double p_172435_, double p_172436_, double p_172437_)
        {
            SuspendedParticle suspendedparticle = new SuspendedParticle(p_172431_, this.sprite, p_172432_, p_172433_, p_172434_, 0.0D, (double) - 0.8F, 0.0D)
            {
                public Optional<ParticleGroup> getParticleGroup()
                {
                    return Optional.of(ParticleGroup.SPORE_BLOSSOM);
                }
            };
            suspendedparticle.lifetime = Mth.randomBetweenInclusive(p_172431_.random, 500, 1000);
            suspendedparticle.gravity = 0.01F;
            suspendedparticle.setColor(0.32F, 0.5F, 0.22F);
            return suspendedparticle;
        }
    }

    public static class UnderwaterProvider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public UnderwaterProvider(SpriteSet p_108063_)
        {
            this.sprite = p_108063_;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double p_108077_, double pY, double p_108079_, double pZ, double p_108081_)
        {
            SuspendedParticle suspendedparticle = new SuspendedParticle(pLevel, this.sprite, pX, p_108077_, pY);
            suspendedparticle.setColor(0.4F, 0.4F, 0.7F);
            return suspendedparticle;
        }
    }

    public static class WarpedSporeProvider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public WarpedSporeProvider(SpriteSet p_108084_)
        {
            this.sprite = p_108084_;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double p_108098_, double pY, double p_108100_, double pZ, double p_108102_)
        {
            double d0 = (double)pLevel.random.nextFloat() * -1.9D * (double)pLevel.random.nextFloat() * 0.1D;
            SuspendedParticle suspendedparticle = new SuspendedParticle(pLevel, this.sprite, pX, p_108098_, pY, 0.0D, d0, 0.0D);
            suspendedparticle.setColor(0.1F, 0.1F, 0.3F);
            suspendedparticle.setSize(0.001F, 0.001F);
            return suspendedparticle;
        }
    }
}