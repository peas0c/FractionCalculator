package com.pack;

import java.util.Scanner;

import static com.pack.Fraction.*;
import static java.lang.Integer.parseInt;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scanner.nextLine();
        while (invalidInput(input)) {
            System.out.println("Неправильно введено выражение" +
                    "\nПопробуйте еще раз или quit");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
        }
        startCalculator(input);
    }
}
