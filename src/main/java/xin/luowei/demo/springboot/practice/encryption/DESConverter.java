package xin.luowei.demo.springboot.practice.encryption;

import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DESConverter implements AttributeConverter<String,String>{

    @Autowired
    private TextEncryptor textEncryptor;
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if(StringUtils.isEmpty(attribute)){
            return attribute;
        }
        return textEncryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if(StringUtils.isEmpty(dbData)){
            return dbData;
        }
        return textEncryptor.decrypt(dbData);
    }
}