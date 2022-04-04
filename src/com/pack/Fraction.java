package com.pack;

import java.util.*;
import java.util.regex.*;

import static java.lang.Integer.parseInt;

public class Fraction {
    private int nominator;
    private int denominator;

    private Fraction(int nominator, int denominator) {
        this.setNominator(nominator);
        this.setDenominator(denominator);
    }

    private Fraction(int nominator) {
        this.setNominator(nominator);
        this.setDenominator(1);
    }

    private Fraction() {
        this.setNominator(1);
        this.setDenominator(1);
    }

    public int getNominator() {
        return this.nominator;
    }

    public void setNominator(int nominator) {
        this.nominator = nominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    private static Fraction sum(Fraction a, Fraction b) {
        Fraction result = new Fraction();

        result.denominator = a.getDenominator() * b.getDenominator();
        result.nominator = a.getNominator() * b.getDenominator() + b.getNominator() * a.getDenominator();

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private Fraction sum(Fraction b) {
        Fraction result = new Fraction();

        result.denominator = this.denominator * b.getDenominator();
        result.nominator = this.getNominator() * b.getDenominator() + b.getNominator() * this.denominator;

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private static Fraction substract(Fraction a, Fraction b) {
        Fraction result = new Fraction();

        result.denominator = a.getDenominator() * b.getDenominator();
        result.nominator = a.getNominator() * b.getDenominator() - b.getNominator() * a.getDenominator();

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private Fraction substract(Fraction b) {
        Fraction result = new Fraction();

        result.denominator = this.denominator * b.getDenominator();
        result.nominator = this.getNominator() * b.getDenominator() - b.getNominator() * this.denominator;

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }


    private static Fraction multiply(Fraction a, Fraction b) {
        Fraction result = new Fraction();

        result.nominator = a.getNominator() * b.getNominator();
        result.denominator = a.getDenominator() * b.getDenominator();

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private Fraction multiply(Fraction a) {
        Fraction result = new Fraction();

        result.nominator = this.getNominator() * a.getNominator();
        result.denominator = this.denominator * a.getDenominator();

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private static Fraction divide(Fraction a, Fraction b) throws Exception {
        Fraction result = new Fraction();

        if (a.getNominator() == 0) throw new Exception("Деление невозможно");

        result.nominator = a.getNominator() * b.getDenominator();
        result.denominator = a.getDenominator() * b.getNominator();

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private Fraction divide(Fraction a) throws Exception {
        Fraction result = new Fraction();

        if (a.getNominator() == 0) throw new Exception("Деление невозможно");

        result.nominator = this.getNominator() * a.getDenominator();
        result.denominator = this.denominator * a.getNominator();

        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    private static int gcd(int a, int b) { //НОД
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }


    public static int getPriority(String value) {
        int priority = 0;
        value = value.trim();
        if (value.matches("-?\\d+/\\-?\\d+")) priority = 0;
        else if (value.equals("(")) priority = 1;
        else if (value.equals(")")) priority = -1;
        else if ((value.equals("-")) | (value.equals("+"))) priority = 2;
        else if ((value.equals("*")) | (value.equals(":"))) priority = 3;
        else if ((value.equals("(")) | (value.equals(")"))) priority = 4;

        return priority;
    }

    private static List expressionToParts(String expression) {
        String expression_copy = expression;
        List<String> result = new ArrayList<String>();
        String regex_for_expression_parts = "(\\s*\\d+(/-?\\d+\\s*)?)|([+*\\-:\\(\\)]\\s*)";
        Pattern pattern = Pattern.compile(regex_for_expression_parts);
        Matcher matcher = pattern.matcher(expression_copy);

        while (matcher.find()) {
            result.add(matcher.group().trim());
        }
        return result;
    }

    public static List expressionToRpn(String expression) {
        List<String> expression_parts = expressionToParts(expression);
        List<String> result = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < expression_parts.size(); i++) {
            String value = expression_parts.get(i);
            if (getPriority(value) == 0) result.add(value);
            else if (getPriority(value) == 1) stack.push(value);
            else if (getPriority(value) > 1) {
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= getPriority(value)) result.add(stack.pop());
                    else break;
                }
                stack.push(value);
            } else if (getPriority(value) == -1) {
                while (getPriority(stack.peek()) > 1) result.add(stack.pop());
                stack.pop();
            }
        }
        while (!stack.empty()) result.add(stack.pop());
        return result;
    }

    private static String calculateTwoFractions(String fraction1, String fraction2, String operation) throws Exception {
        Fraction one;
        Fraction two;
        Fraction result = null;

        if (fraction1.contains("/")) { //создание элемента класса для первой дроби
            String[] input_args = fraction1.trim().split("/"); // разбивает на числ и знамен
            one = new Fraction(parseInt(input_args[0]), parseInt(input_args[1]));
        } else if (fraction1.equals("")) one = new Fraction();
        else one = new Fraction(parseInt(fraction1));

        if (fraction2.contains("/")) { //создание элемента класса для второй дроби
            String[] input_args = fraction2.trim().split("/"); // разбивает на числ и знамен
            two = new Fraction(parseInt(input_args[0]), parseInt(input_args[1]));
        } else if (fraction2.equals("")) two = new Fraction();
        else two = new Fraction(parseInt(fraction2));

        if (operation.equals("+")) result = sum(one, two);
        if (operation.equals("-")) result = substract(one, two);
        if (operation.equals("*")) result = multiply(one, two);
        if (operation.equals(":")) result = divide(one, two);

        return fractionToString(result);
    }


    private static String calculateRPN(List<String> rpn) throws Exception {
        Stack stack = new Stack<>();
        for (int i = 0; i < rpn.size(); i++) {
            String value = rpn.get(i);
            if (value.matches("-?\\d+(/-?\\d+\\s*)?")) stack.push(value);
            else {
                String fraction2 = stack.pop().toString();
                String fraction1 = stack.pop().toString();
                stack.push(calculateTwoFractions(fraction1, fraction2, value));
            }
        }
        return stack.pop().toString();
    }


    public static void startCalculator(String input) throws Exception {
        System.out.println("= " + calculateRPN(expressionToRpn(input)));
    }


    public static boolean invalidInput(String input) {
        return !input.matches("^((\\s+)?\\(*-?\\d+(\\/(?!0+|-0+)(-?\\d+))?(\\s+)?" +  //первое значение
                "[+\\-*:](\\s+)?-?\\d+(\\/(?!0+|-0+)(-?\\d+))?\\)*(\\s+)?)" +               //второе значение
                "((([+\\-*:](\\s+)?-?\\d+(\\/(?!0+|-0+)(-?\\d+))?\\)*(\\s+)?)*)|" +  //формат "операция значение"
                "((([+\\-*:](\\s+)?\\(*-?\\d+(\\/(?!0+|-0+)(-?\\d+))?(\\s+)?" +      //формат "операция(значение
                "[+\\-*:](\\s+)?-?\\d+(\\/(?!0+|-0+)(-?\\d+))?\\)*(\\s+)?)*)))*$")   //операция значение)"
                | !(rightAmountOfBrackets(input));
    }


    private static boolean rightAmountOfBrackets(String expr) {
        int opened_brackets = expr.length() - expr.replace("(", "").length();
        int closed_brackets = expr.length() - expr.replace("(", "").length();

        return opened_brackets == closed_brackets;
    }

    public static void printFraction(Fraction a) {
        System.out.printf("%d / %d\n", a.getNominator(), a.getDenominator());
    }


    public static String fractionToString(Fraction a) {
        return (a.getNominator() + "/" + a.getDenominator());
    }

}
