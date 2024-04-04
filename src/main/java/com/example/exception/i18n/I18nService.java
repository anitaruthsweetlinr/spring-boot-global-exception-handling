package com.example.exception.i18n;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * With each HTTP Servlet Request a language (locale) is provided within
 * the Accept-Language Header.
 * This class returns the related message in the provided request locale.
 * The message files are located in resources/i18n
 *
 * @author Jeffrey Spaan
 * @since 2024-04-04
 */

@Service
@RequiredArgsConstructor
public class I18nService {

    private final MessageSource messageSource;

    private final HttpServletRequest request;

    // The logging language is set within the application.yml file
    @Value("${spring.messages.logging-language}")
    private String loggingLanguage;

    public String getLogMessage(String code) {
        return messageSource.getMessage(code, null, Locale.of(loggingLanguage));
    }

    public String getMessage(String code, @Nullable String... args) {
        return messageSource.getMessage(code, args, request.getLocale());
    }
}