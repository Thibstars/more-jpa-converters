package com.github.thibstars.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

/**
 * Converter that converts a Set of Strings to a String in JSon format and vice-versa.
 * This converter expects a SequencedSet type, as we want to preserve the ordering from and to the database to prevent confusion.
 *
 * @author Thibault Helsmoortel
 */
@Converter
public class StringSetAttributeConverter implements AttributeConverter<SequencedSet<String>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(SequencedSet<String> attribute) {
        return attribute != null ? OBJECT_MAPPER.writeValueAsString(attribute) : null;
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked") // Unchecked while reading value, but we actually know the type to use
    public SequencedSet<String> convertToEntityAttribute(String dbData) {
        return dbData != null
                ? OBJECT_MAPPER.readValue(dbData, LinkedHashSet.class)
                : null;
    }
}
