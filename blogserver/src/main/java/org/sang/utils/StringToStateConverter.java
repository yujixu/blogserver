package org.sang.utils;

import org.sang.bean.constant.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class StringToStateConverter implements Converter<String, State> {
    @Override
    public State convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        return State.get(Integer.parseInt(value));
    }
}