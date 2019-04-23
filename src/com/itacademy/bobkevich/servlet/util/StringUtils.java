package com.itacademy.bobkevich.servlet.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;

import static java.util.Objects.*;

@UtilityClass
public class StringUtils {

    private static final String EMPTY = "";

    public static boolean isEmpty(String value) {
        return isNull(value) || EMPTY.equals(value.trim());
    }
}
