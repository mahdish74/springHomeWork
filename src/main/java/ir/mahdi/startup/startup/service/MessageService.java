package ir.mahdi.startup.startup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    @Autowired
    private ResourceBundleMessageSource messageSource;



    public String getMessage(String key){
        return messageSource.getMessage(key,null,Locale.US);
    }

}