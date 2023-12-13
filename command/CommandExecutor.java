package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.Operation;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandExecutor {

    private static final Map<Operation, Command> allKnownCommandsMap;

    private CommandExecutor() {

    }

    static {
        allKnownCommandsMap = new LinkedHashMap<>();
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptedOperationException {
        allKnownCommandsMap.get(operation).execute();
    }
}
