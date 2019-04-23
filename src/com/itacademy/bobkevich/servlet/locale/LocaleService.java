package com.itacademy.bobkevich.servlet.locale;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleService {

    private static final LocaleService LOCALE_SERVICE = new LocaleService();
    private static final String BUNDLE_NAME = "messages";
    private static final Map<String, Locale> LOCALES = new HashMap<>();

    static {
        LOCALES.put("rus", new Locale("ru", "RU"));
        LOCALES.put("eng", new Locale("en", "UK"));

    }

    public String getMessage(String languageCode, String messageKey) {
        Locale locale = LOCALES.getOrDefault(languageCode, Locale.getDefault());
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return bundle.getString(messageKey);
    }

    public static LocaleService getLocaleService() {
        return LOCALE_SERVICE;
    }
}
