package me.biabani.n2ac.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.biabani.n2ac.converter.Converter;
import me.biabani.n2ac.converter.impl.PersianConverter;

@Getter
@RequiredArgsConstructor
public enum Language {

    PERSIAN(PersianConverter.class);

    private final Class<? extends Converter> converter;
}
