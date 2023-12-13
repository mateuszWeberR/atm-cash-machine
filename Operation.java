package com.codegym.task.task26.task2613;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        try {
            for (Operation operation : Operation.values()) {
                if (operation.ordinal() == i) {
                    if (operation.ordinal() == 0)
                        throw new IllegalArgumentException();
                    return operation;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }
}
