package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle
            (CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptedOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean tryAgain = false;

        while (true) {
            try {
                if (!tryAgain)
                    ConsoleHelper.writeMessage(res.getString("specify.data"));
                else
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));

                String cardNumber = ConsoleHelper.readString().trim();
                String pin = ConsoleHelper.readString().trim();

                if (validCreditCards.containsKey(cardNumber)
                        && validCreditCards.getString(cardNumber).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                            cardNumber));
                    return;
                }

                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),
                        cardNumber));
                tryAgain = true;
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));

            } catch (InterruptedOperationException | NullPointerException ignored) {
                ConsoleHelper.writeMessage("Invalid card number or pin. Please try Again.");
            }
        }
    }
}
