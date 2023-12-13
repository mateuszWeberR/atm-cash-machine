package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.CurrencyManipulator;
import com.codegym.task.task26.task2613.CurrencyManipulatorFactory;
import com.codegym.task.task26.task2613.exception.InsufficientFundsException;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.requestCurrencyCode();

        CurrencyManipulator manipulator =
                CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        int amount;

        while (true) {

            while (true) {
                amount = askAboutAmount();
                if (!manipulator.isAmountAvailable(amount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                } else {
                    break;
                }
            }

            try {
                Map<Integer, Integer> withdrawBanknotes = manipulator.withdrawAmount(amount);
                displayResult(withdrawBanknotes);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                        amount, currencyCode));
                return;
            } catch (InsufficientFundsException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }

    private int askAboutAmount() throws InterruptedOperationException {
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                String amount = ConsoleHelper.readString();
                int amountInt = Integer.parseInt(amount);
                if (amountInt > 0)
                    return amountInt;
                else
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
    }

    private void displayResult(Map<Integer, Integer> withdrawBanknotes) {
        withdrawBanknotes.forEach((k, v)
                -> ConsoleHelper.writeMessage("\t" + k + " - " + v));
    }
}
