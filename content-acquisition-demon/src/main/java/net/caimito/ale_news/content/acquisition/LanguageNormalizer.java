package net.caimito.ale_news.content.acquisition;

public class LanguageNormalizer {

    public static String normalize(String language) {
        String lang = language.toLowerCase() ;

        if (lang.contains("-"))
            return lang.split("-")[0];
        else
            return lang ;
    }
}
