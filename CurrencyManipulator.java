package com.codegym.task.task26.task2613;

import com.codegym.task.task26.task2613.exception.InsufficientFundsException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new TreeMap<>(Comparator.comparingInt(Integer::intValue).reversed());
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int currentValue = denominations.get(denomination);
            denominations.put(denomination, currentValue + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            totalAmount += entry.getKey() * entry.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    /* Return smallest number of banknotes - if several options
       return largest number banknotes of the highest denomination */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws InsufficientFundsException {
        Map<Integer, Integer> temp = new TreeMap<>(Comparator.comparingInt(Integer::intValue).reversed());
        temp.putAll(denominations);
        Map<Integer, Integer> resultMap =
                new TreeMap<>(Comparator.comparingInt(Integer::intValue).reversed());

        int currentAmount = 0;

        for (Integer banknote : temp.keySet()) {
            while (banknote <= expectedAmount && temp.get(banknote) > 0
                    && (currentAmount + banknote <= expectedAmount)) {

                currentAmount += banknote;
                int newValue = temp.get(banknote) - 1;
                temp.put(banknote, newValue);

                if (!resultMap.containsKey(banknote)) {
                    resultMap.put(banknote, 1);
                } else {
                    int value = resultMap.get(banknote);
                    resultMap.put(banknote, ++value);
                }

                if (currentAmount == expectedAmount) {
                    /* When we succeed, we must update current denominations map
                       and return the result */
                    denominations = temp;
                    return resultMap;
                }
            }
        }

        // When we don't have banknotes for withdrawal
        throw new InsufficientFundsException();
    }
}
