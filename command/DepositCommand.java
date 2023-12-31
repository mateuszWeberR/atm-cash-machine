package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.CurrencyManipulator;
import com.codegym.task.task26.task2613.CurrencyManipulatorFactory;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.requestCurrencyCode();

        CurrencyManipulator manipulator =
                CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while(true) {
            try {
                String[] denominationAndAmount = ConsoleHelper.getTwoValidNumbers(currencyCode);

                int denomination = Integer.parseInt(denominationAndAmount[0]);
                int count = Integer.parseInt(denominationAndAmount[1]);

                manipulator.addAmount(denomination, count);

                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                        (denomination * count), currencyCode));
                break;
            } catch (NumberFormatException ignored) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
