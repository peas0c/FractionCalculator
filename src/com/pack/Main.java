package com.pack;

import java.util.Scanner;

import static com.pack.Fraction.*;
import static java.lang.Integer.parseInt;


public class Main {

    public static void main(String[] args) throws Exception {
        Fraction one;
        Fraction two;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение" +
                "в формате \"значение операция(+-*:) значение\"");
        String input = scanner.nextLine();
        while (invalidInput(input)) {
            System.out.println("Неправильно введено выражение" +
                    "\nПопробуйте еще раз или quit");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
        }
        String[] input_values = input.trim().split("[\\+\\-*:]");
        if (input_values[0].contains("/")) {
            String[] input_args = input_values[0].trim().split("/");
            one = new Fraction(parseInt(input_args[0]), parseInt(input_args[1]));
        } else if (input.equals("")) {
            one = new Fraction();
        } else {
            one = new Fraction(parseInt(input));
        }
        if (input_values[1].contains("/")) {
            String[] input_args = input_values[1].trim().split("/");
            two = new Fraction(parseInt(input_args[0]), parseInt(input_args[1]));
        } else if (input.equals("")) {
            two = new Fraction();
        } else {
            two = new Fraction(parseInt(input));
        }

        if (input.contains("+")) {
            System.out.print(" = "
                    + sum(one, two).getNominator() + "/" + sum(one, two).getDenominator());
        }
        if (input.contains("-")) {
            System.out.print(" = " + substract(one, two).getNominator() + "/" + substract(one, two).getDenominator());
        }
        if (input.contains("*")) {
            System.out.print(" = " + multiply(one, two).getNominator() + "/" + multiply(one, two).getDenominator());
        }
        if (input.contains(":")) {
            System.out.print(" = " + divide(one, two).getNominator() + "/" + divide(one, two).getDenominator());
        }

    }
    public static boolean invalidInput(String input){
        if (input.matches("^(\\s+)?+-?\\d+(\\/(?!0+|-0+)(-?\\d+))?(\\s+)?[+\\-*:](\\s+)?-?\\d+(\\/(?!0+|-0+)(-?\\d+))?(\\s+)?$")) {
            return false;
        }else{
            return true;
            }
    }
}
