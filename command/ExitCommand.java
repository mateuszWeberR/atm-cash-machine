package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));

        String respond = ConsoleHelper.readString();

        if (respond != null && respond.equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
