package com.pack;

public class Operation extends Fraction{
    private String symbol;

    public Operation(String symbol){
        this.symbol = symbol;
    }

    public String getOperation() {
        return symbol;
    }
    public void setOperation(String symbol) {
        this.symbol = symbol;
    }

    public static boolean validOperation(String operation) {
        if (operation.matches("^[\\+\\-/\\*]$")) {
            return true;
        } else {
            return false;
        }
    }
}
