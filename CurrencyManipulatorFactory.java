package com.codegym.task.task26.task2613;

import java.util.*;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {

    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        currencyCode = currencyCode.toUpperCase();
        if (!map.containsKey(currencyCode)) {
            CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, manipulator);
            return manipulator;
        } else {
            return map.get(currencyCode);
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
