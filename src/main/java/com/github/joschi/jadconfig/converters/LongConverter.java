package com.github.joschi.jadconfig.converters;

import com.github.joschi.jadconfig.Converter;
import com.github.joschi.jadconfig.ParameterException;

/**
 * Converter for type {@link Long}
 *
 * @author jschalanda
 */
public class LongConverter implements Converter<Long> {

    @Override
    public Long convertFrom(String value) {

        Long result;

        try {
            result = Long.valueOf(value);
        } catch (NumberFormatException ex) {

            throw new ParameterException("Couldn't convert value \"" + value + "\" to Long.", ex);
        }

        return result;
    }

    @Override
    public String convertTo(Long value) {

        if (value == null) {
            throw new ParameterException("Couldn't convert \"null\" to String.");
        }

        return value.toString();
    }
}
