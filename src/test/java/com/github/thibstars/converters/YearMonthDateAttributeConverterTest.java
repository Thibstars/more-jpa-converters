package com.github.thibstars.converters;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Thibault Helsmoortel
 */
class YearMonthDateAttributeConverterTest {

    private static final Date DB_REPRESENTATION = Date.valueOf(LocalDate.from(YearMonth.now().atDay(1)));
    private static final YearMonth ENTITY_REPRESENTATION = YearMonth.now();

    private YearMonthDateAttributeConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new YearMonthDateAttributeConverter();
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
                "Result must be a year-month date (at day 1, since the day is ignored in the conversion) eg.: '2024-04-01'."
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
                "Result must be a parsed YearMonth object."
        );
    }
}