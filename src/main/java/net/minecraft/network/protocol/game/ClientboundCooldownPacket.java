package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.item.Item;

public class ClientboundCooldownPacket implements Packet<ClientGamePacketListener>
{
    private final Item item;
    private final int duration;

    public ClientboundCooldownPacket(Item p_132000_, int p_132001_)
    {
        this.item = p_132000_;
        this.duration = p_132001_;
    }

    public ClientboundCooldownPacket(FriendlyByteBuf p_178831_)
    {
        this.item = Item.byId(p_178831_.readVarInt());
        this.duration = p_178831_.readVarInt();
    }

    public void write(FriendlyByteBuf pBuf)
    {
        pBuf.writeVarInt(Item.getId(this.item));
        pBuf.writeVarInt(this.duration);
    }

    public void handle(ClientGamePacketListener pHandler)
    {
        pHandler.handleItemCooldown(this);
    }

    public Item getItem()
    {
        return this.item;
    }

    public int getDuration()
    {
        return this.duration;
    }
}
