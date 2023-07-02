package com.fiap.techchallenge.fourlanches.domain;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class AssertationConcern {

    public static boolean isNotEmpty(String string) {
        return !StringUtils.isEmpty(string);
    }

    public static boolean isPositive(BigDecimal number) {
        return !ObjectUtils.isEmpty(number) && (number.compareTo(BigDecimal.ZERO) > 0);
    }

}
