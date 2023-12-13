package com.codegym.task.task26.task2613;

import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptedOperationException {
        try {
            String input = bis.readLine();
            if (input.equalsIgnoreCase("EXIT"))
                throw new InterruptedOperationException();

            return input;
        } catch (IOException ignored) {

        }
        return null;
    }

    public static String requestCurrencyCode() throws InterruptedOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String input = readString().trim();

            if (input.length() == 3)
                return input.toUpperCase();
            else
                writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getTwoValidNumbers(String currencyCode) throws InterruptedOperationException {
        while (true) {
            try {
                writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
                String[] numbers = readString().trim().split(" ");
                int banknote = Integer.parseInt(numbers[0]);
                int amount = Integer.parseInt(numbers[1]);
                if (numbers.length == 2 && banknote > 0 && amount > 0)
                    return numbers;
                else
                    writeMessage(res.getString("invalid.data"));

            } catch (ArrayIndexOutOfBoundsException | NullPointerException |
                    PatternSyntaxException | NumberFormatException ignored) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation requestOperation() throws InterruptedOperationException {
        while (true) {
            ConsoleHelper.writeMessage(
                    "\n" + res.getString("choose.operation") + "\n" +
                            "1 - " + res.getString("operation.INFO") + "\n" +
                            "2 - " + res.getString("operation.DEPOSIT") + "\n" +
                            "3 - " + res.getString("operation.WITHDRAW") + "\n" +
                            "4 - " + res.getString("operation.EXIT"));

            String num = readString();
            int number = Integer.parseInt(num);
            if (number > 0 && number <= 4) {
                Operation operation = Operation.getAllowableOperationByOrdinal(number);
                return operation;
            }

            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
