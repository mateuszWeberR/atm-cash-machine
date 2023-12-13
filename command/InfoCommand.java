package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.CurrencyManipulator;
import com.codegym.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));

        Collection<CurrencyManipulator> list =
                CurrencyManipulatorFactory.getAllCurrencyManipulators();

        boolean hasMoney = false;

        for (CurrencyManipulator manipulator : list) {
            if (manipulator.hasMoney()) {
                hasMoney = true;
                ConsoleHelper.writeMessage("\t" + manipulator.getCurrencyCode() + " - "
                        + manipulator.getTotalAmount());
            }
        }

        if (!hasMoney)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
