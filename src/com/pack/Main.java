package com.pack;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nom = scanner.nextInt();
        int denom = scanner.nextInt();
        Fraction one = new Fraction(nom, denom);

        System.out.println(one.getNominator()+ "/" + one.getNominator());
    }

}
