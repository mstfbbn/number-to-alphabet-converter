package me.biabani.n2ac.converter;

import me.biabani.n2ac.constants.Language;

import java.lang.reflect.InvocationTargetException;

//@FunctionalInterface
public interface Converter {
    
    String convert(String numberAsString);

    static Converter of(Language language) {
        assert language != null;
        try {
            return language.getConverter().getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
