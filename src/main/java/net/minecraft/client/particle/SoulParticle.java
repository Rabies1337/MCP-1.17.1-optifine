package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;

public class SoulParticle extends RisingParticle
{
    private final SpriteSet sprites;

    SoulParticle(ClientLevel p_107717_, double p_107718_, double p_107719_, double p_107720_, double p_107721_, double p_107722_, double p_107723_, SpriteSet p_107724_)
    {
        super(p_107717_, p_107718_, p_107719_, p_107720_, p_107721_, p_107722_, p_107723_);
        this.sprites = p_107724_;
        this.scale(1.5F);
        this.setSpriteFromAge(p_107724_);
    }

    public ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick()
    {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public Provider(SpriteSet p_107739_)
        {
            this.sprite = p_107739_;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double p_107753_, double pY, double p_107755_, double pZ, double p_107757_)
        {
            SoulParticle soulparticle = new SoulParticle(pLevel, pX, p_107753_, pY, p_107755_, pZ, p_107757_, this.sprite);
            soulparticle.setAlpha(1.0F);
            return soulparticle;
        }
    }
}
