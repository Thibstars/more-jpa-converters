package com.github.thibstars.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.YearMonth;

/**
 * Converter that converts a date to a YearMonth and vice-versa.
 *
 * @author Thibault Helsmoortel
 */
@Converter
public class YearMonthDateAttributeConverter implements AttributeConverter<YearMonth, Date> {

    @Override
    public Date convertToDatabaseColumn(YearMonth yearMonth) {
        if (yearMonth != null) {
            return Date.valueOf(yearMonth.atDay(1));
        }

        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(Date dbData) {
        if (dbData != null) {
            return YearMonth.from(dbData.toLocalDate());
        }

        return null;
    }
}