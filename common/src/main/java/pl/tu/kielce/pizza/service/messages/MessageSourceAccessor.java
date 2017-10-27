package pl.tu.kielce.pizza.service.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageSourceAccessor {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String messageCode, Object... parameters) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, parameters,  locale);
    }

}
