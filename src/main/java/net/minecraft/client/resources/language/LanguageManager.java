package net.minecraft.client.resources.language;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Stream;
import net.minecraft.client.resources.metadata.language.LanguageMetadataSection;
import net.minecraft.locale.Language;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageManager implements ResourceManagerReloadListener
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String DEFAULT_LANGUAGE_CODE = "en_us";
    private static final LanguageInfo DEFAULT_LANGUAGE = new LanguageInfo("en_us", "US", "English", false);
    private Map<String, LanguageInfo> languages = ImmutableMap.of("en_us", DEFAULT_LANGUAGE);
    private String currentCode;
    private LanguageInfo currentLanguage = DEFAULT_LANGUAGE;

    public LanguageManager(String p_118971_)
    {
        this.currentCode = p_118971_;
    }

    private static Map<String, LanguageInfo> extractLanguages(Stream<PackResources> p_118982_)
    {
        Map<String, LanguageInfo> map = Maps.newHashMap();
        p_118982_.forEach((p_118980_) ->
        {
            try {
                LanguageMetadataSection languagemetadatasection = p_118980_.getMetadataSection(LanguageMetadataSection.SERIALIZER);

                if (languagemetadatasection != null)
                {
                    for (LanguageInfo languageinfo : languagemetadatasection.getLanguages())
                    {
                        map.putIfAbsent(languageinfo.getCode(), languageinfo);
                    }
                }
            }
            catch (IOException | RuntimeException runtimeexception)
            {
                LOGGER.warn("Unable to parse language metadata section of resourcepack: {}", p_118980_.getName(), runtimeexception);
            }
        });
        return ImmutableMap.copyOf(map);
    }

    public void onResourceManagerReload(ResourceManager pResourceManager)
    {
        this.languages = extractLanguages(pResourceManager.listPacks());
        LanguageInfo languageinfo = this.languages.getOrDefault("en_us", DEFAULT_LANGUAGE);
        this.currentLanguage = this.languages.getOrDefault(this.currentCode, languageinfo);
        List<LanguageInfo> list = Lists.newArrayList(languageinfo);

        if (this.currentLanguage != languageinfo)
        {
            list.add(this.currentLanguage);
        }

        ClientLanguage clientlanguage = ClientLanguage.loadFrom(pResourceManager, list);
        I18n.setLanguage(clientlanguage);
        Language.inject(clientlanguage);
    }

    public void setSelected(LanguageInfo pCurrentLanguage)
    {
        this.currentCode = pCurrentLanguage.getCode();
        this.currentLanguage = pCurrentLanguage;
    }

    public LanguageInfo getSelected()
    {
        return this.currentLanguage;
    }

    public SortedSet<LanguageInfo> getLanguages()
    {
        return Sets.newTreeSet(this.languages.values());
    }

    public LanguageInfo getLanguage(String p_118977_)
    {
        return this.languages.get(p_118977_);
    }
}
