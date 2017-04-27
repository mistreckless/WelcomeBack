package com.welcome.server.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? String.join(",", strings) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return s != null ? new ArrayList<>(Arrays.asList(s.split(","))) : null;
    }
}
