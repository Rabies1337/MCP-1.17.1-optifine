package com.mojang.realmsclient.dto;

import com.google.gson.JsonObject;
import com.mojang.realmsclient.util.JsonUtils;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PendingInvite extends ValueObject
{
    private static final Logger LOGGER = LogManager.getLogger();
    public String invitationId;
    public String worldName;
    public String worldOwnerName;
    public String worldOwnerUuid;
    public Date date;

    public static PendingInvite parse(JsonObject p_87431_)
    {
        PendingInvite pendinginvite = new PendingInvite();

        try
        {
            pendinginvite.invitationId = JsonUtils.getStringOr("invitationId", p_87431_, "");
            pendinginvite.worldName = JsonUtils.getStringOr("worldName", p_87431_, "");
            pendinginvite.worldOwnerName = JsonUtils.getStringOr("worldOwnerName", p_87431_, "");
            pendinginvite.worldOwnerUuid = JsonUtils.getStringOr("worldOwnerUuid", p_87431_, "");
            pendinginvite.date = JsonUtils.getDateOr("date", p_87431_);
        }
        catch (Exception exception)
        {
            LOGGER.error("Could not parse PendingInvite: {}", (Object)exception.getMessage());
        }

        return pendinginvite;
    }
}
