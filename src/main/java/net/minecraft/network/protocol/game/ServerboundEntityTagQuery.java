package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;

public class ServerboundEntityTagQuery implements Packet<ServerGamePacketListener>
{
    private final int transactionId;
    private final int entityId;

    public ServerboundEntityTagQuery(int p_134018_, int p_134019_)
    {
        this.transactionId = p_134018_;
        this.entityId = p_134019_;
    }

    public ServerboundEntityTagQuery(FriendlyByteBuf p_179594_)
    {
        this.transactionId = p_179594_.readVarInt();
        this.entityId = p_179594_.readVarInt();
    }

    public void write(FriendlyByteBuf pBuf)
    {
        pBuf.writeVarInt(this.transactionId);
        pBuf.writeVarInt(this.entityId);
    }

    public void handle(ServerGamePacketListener pHandler)
    {
        pHandler.handleEntityTagQuery(this);
    }

    public int getTransactionId()
    {
        return this.transactionId;
    }

    public int getEntityId()
    {
        return this.entityId;
    }
}
