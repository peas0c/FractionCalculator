package com.pack;

import java.util.Scanner;

import static com.pack.Operation.validOperation;
import static java.lang.Integer.parseInt;

public class Main extends Fraction {

    public static void main(String[] args) {
        Fraction one;
        Fraction two;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первую дробь или число");
        String input = scanner.nextLine();
        while (!validFraction(input)) {
            System.out.println("Неправильно введена дробь" +
                    "\nПопробуйте еще раз или quit");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
        }
        if (input.contains("/")) {
            String[] input_args = input.split("/");
            one = new Fraction(parseInt(input_args[0]), parseInt(input_args[1]));
        } else if (input.equals("")) {
            one = new Fraction();
        } else {
            one = new Fraction(parseInt(input));
        }
        System.out.println("Введите вторую дробь или целое число");
        input = scanner.nextLine();
        while (!validFraction(input)) {
            System.out.println("Неправильно введена дробь" +
                    "\nПопробуйте еще раз или quit");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
        }
        if (input.contains("/")) {
            String[] input_args = input.split("/");
            two = new Fraction(parseInt(input_args[0]), parseInt(input_args[1]));
        } else if (input.equals("")) {
            two = new Fraction();
        } else {
            two = new Fraction(parseInt(input));
        }

        System.out.println("Введите операцию +-*/");
        input = scanner.nextLine();
        while (!validOperation(input)) {
            System.out.println("Неправильно введена операция" +
                    "\nПопробуйте еще раз или quit");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
        }
        Operation operation = new Operation(input);

        if (operation.getOperation().equals("+")) {
            System.out.println("sum(one, two):  "
                    + sum(one, two).getNominator() + "/" + sum(one, two).getDenominator());
            System.out.println("one.sum(two)  " + one.sum(two).getNominator() + "/" + one.sum(two).getDenominator());
        }
        if (operation.getOperation().equals("-")) {
            System.out.println("substract(one, two):  " + substract(one, two).getNominator() + "/" + substract(one, two).getDenominator());
            System.out.println("one.substract(two)  " + one.substract(two).getNominator() + "/" + one.substract(two).getDenominator());
        }
        if (operation.getOperation().equals("*")) {
            System.out.println("multiply(one, two):  " + multiply(one, two).getNominator() + "/" + multiply(one, two).getDenominator());
            System.out.println("one.multiply(two)  " + one.multiply(two).getNominator() + "/" + one.multiply(two).getDenominator());
        }
        if (operation.getOperation().equals("/")) {
            System.out.println("divide(one, two):  " + divide(one, two).getNominator() + "/" + divide(one, two).getDenominator());
            System.out.println("one.divide(two)  " + one.divide(two).getNominator() + "/" + one.divide(two).getDenominator());
        }
    }
}
