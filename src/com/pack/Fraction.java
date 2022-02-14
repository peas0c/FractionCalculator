package com.pack;

public class Fraction {
    int nominator;
    int denominator;

    public Fraction(int nominator, int denominator) {
        this.nominator = nominator;
        this.denominator = denominator;
    }

    public Fraction(int nominator) {
        this.nominator = nominator;
        this.denominator = 1;
    }

    public Fraction() {
        this.nominator = 1;
        this.denominator = 1;
    }

    public int getNominator() {
        return nominator;
    }

    public void setNominator(int nominator) {
        this.nominator = nominator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public static Fraction sum(Fraction a, Fraction b) {
        Fraction result = new Fraction();
        if (a.denominator == b.denominator) {
            result.nominator = a.nominator + b.nominator;
            result.denominator = a.denominator;
        } else if (a.denominator % b.denominator == 0) {
            result.nominator = a.nominator + b.nominator * (a.denominator/b.denominator);
            result.denominator = a.denominator;
        } else if (b.denominator % a.denominator == 0) {
            result.nominator = b.nominator + a.nominator * (b.denominator/a.denominator);
            result.denominator = b.denominator;
        } else {
            result.nominator = a.nominator * b.denominator + b.nominator * a.denominator;
            result.denominator = a.denominator * b.denominator;
        }
        return new Fraction(result.nominator, result.denominator);
    }

    public Fraction sum(Fraction a) {
        if (this.denominator == a.denominator) {
            this.nominator += a.nominator;
        }
        else if (this.denominator == 1) {
            this.nominator = this.nominator * a.denominator + a.nominator;
            this.denominator = a.denominator;
        } else if (a.denominator == 1) {
            this.nominator = a.nominator * this.denominator + this.nominator;
        } else {
            this.nominator = this.nominator * a.denominator + a.nominator * this.denominator;
            this.denominator *= a.denominator;
        }
        return new Fraction(this.nominator, this.denominator);
    }

    public static Fraction substract(Fraction a, Fraction b) {
        Fraction result = new Fraction();
        if (a.denominator == b.denominator) {
            result.nominator = a.nominator - b.nominator;
            result.denominator = a.denominator;
        } else if (b.denominator == 1) {
            result.nominator = a.nominator - b.nominator * a.denominator;
            result.denominator = a.denominator;
        } else if (a.denominator == 1) {
            result.nominator = a.nominator * b.denominator - b.nominator;
            result.denominator = b.denominator;
        } else {
            result.nominator = a.nominator * b.denominator - b.nominator * a.denominator;
            result.denominator = a.denominator * b.denominator;
        }
        if (gcd(result.nominator, result.denominator) != 1) {
            result.nominator /= gcd(result.nominator, result.denominator);
            result.denominator /= gcd(result.nominator, result.denominator);
        }
        return new Fraction(result.nominator, result.denominator);
    }

    public Fraction substract(Fraction a) {
        if (this.denominator == a.denominator) {
            this.nominator += a.nominator;
        }
        else if (this.denominator == 1) {
            this.nominator = this.nominator * a.denominator - a.nominator;
            this.denominator = a.denominator;
        } else if (a.denominator == 1) {
            this.nominator = this.nominator - a.nominator * this.denominator;
        } else {
            this.nominator = this.nominator * a.denominator - a.nominator * this.denominator;
            this.denominator *= a.denominator;
        }
        if (gcd(this.nominator, this.denominator) != 1) {
            this.nominator /= gcd(this.nominator, this.denominator);
            this.denominator /= gcd(this.nominator, this.denominator);
        }
        return new Fraction(this.nominator, this.denominator);
    }

    public static Fraction multiply(Fraction a, Fraction b) {
        Fraction result = new Fraction();
        result.nominator = a.nominator * b.nominator;
        result.denominator = a.denominator * b.denominator;
        if (gcd(result.nominator, result.denominator) != 1) {
            result.nominator /= gcd(result.nominator, result.denominator);
            result.denominator /= gcd(result.nominator, result.denominator);
        }
        return new Fraction(result.nominator, result.denominator);
    }

    public Fraction multiply(Fraction a) {
        this.nominator *= a.nominator;
        this.denominator = a.denominator;
        if (gcd(this.nominator, this.denominator) != 1) {
            this.nominator /= gcd(this.nominator, this.denominator);
            this.denominator /= gcd(this.nominator, this.denominator);
        }
        return new Fraction(this.nominator, this.denominator);
    }

    public static Fraction divide(Fraction a, Fraction b) {
        Fraction result = new Fraction();
        if (b.nominator == 0) {
            System.out.println("Невозможно провести деление");
        }
        result.nominator = a.nominator * b.denominator;
        result.denominator = a.denominator * b.nominator;
        if (gcd(result.nominator, result.denominator) != 1) {
            result.nominator /= gcd(result.nominator, result.denominator);
            result.denominator /= gcd(result.nominator, result.denominator);
        }
        return new Fraction(result.nominator, result.denominator);
    }

    public Fraction divide(Fraction a) {
        if (a.nominator == 0) {
            System.out.println("Невозможно провести деление");
        }
        this.nominator *= a.denominator;
        this.denominator *= a.nominator;
        if (gcd(this.nominator, this.denominator) != 1) {
            this.nominator /= gcd(this.nominator, this.denominator);
            this.denominator /= gcd(this.nominator, this.denominator);
        }
        return new Fraction(this.nominator, this.denominator);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static boolean validFraction(String input) {
        if (input.contains("/")) {
            String[] input_args = input.split("/");
            if (input_args[0].matches("^-?\\d+$")
                    && input_args[1].matches("^(?!0+|-0+)(-?\\d+)$")
                    && input.matches("^-?\\d+/-?\\d+$")) {
                return true;
            } else {
                System.out.println("Ошибка ввода");
                return false;
            }
        } else {
            if (input.matches("^-?\\d+$|^$")) {
                return true;
            } else {
                System.out.println("Ошибка ввода");
                return false;
            }
        }System.out.println(gcd(21,3))
    }
}