package com.mojang.realmsclient.dto;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.realmsclient.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsNews extends ValueObject
{
    private static final Logger LOGGER = LogManager.getLogger();
    public String newsLink;

    public static RealmsNews parse(String p_87472_)
    {
        RealmsNews realmsnews = new RealmsNews();

        try
        {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_87472_).getAsJsonObject();
            realmsnews.newsLink = JsonUtils.getStringOr("newsLink", jsonobject, (String)null);
        }
        catch (Exception exception)
        {
            LOGGER.error("Could not parse RealmsNews: {}", (Object)exception.getMessage());
        }

        return realmsnews;
    }
}
