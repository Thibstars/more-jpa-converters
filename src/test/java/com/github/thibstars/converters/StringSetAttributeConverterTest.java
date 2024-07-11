package com.github.thibstars.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;

/**
 * @author Thibault Helsmoortel
 */
class StringSetAttributeConverterTest {

    private static final String DB_REPRESENTATION = "[\"ella.stiek@email.com\",\"isabel.kapot@email.com\"]";
    private static final LinkedHashSet<String> ENTITY_REPRESENTATION = new LinkedHashSet<>();

    static {
        ENTITY_REPRESENTATION.add("ella.stiek@email.com");
        ENTITY_REPRESENTATION.add("isabel.kapot@email.com");
    }

    private StringSetAttributeConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new StringSetAttributeConverter();
    }

    @Test
    void shouldConvertToDatabaseColumn() {
        Assertions.assertNull(
                converter.convertToDatabaseColumn(null),
                "Providing null must result in null."
        );

        Assertions.assertEquals(
                DB_REPRESENTATION,
                converter.convertToDatabaseColumn(ENTITY_REPRESENTATION),
                "Result must be values separated by ','."
        );
    }

    @Test
    void shouldConvertToEntityData() {
        Assertions.assertNull(
                converter.convertToEntityAttribute(null),
                "Providing null must result in null."
        );

        Assertions.assertEquals(
                ENTITY_REPRESENTATION,
                converter.convertToEntityAttribute(DB_REPRESENTATION),
                "Result must be values separated by ','."
        );
    }
}
