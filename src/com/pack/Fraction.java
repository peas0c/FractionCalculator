package com.pack;

public class Fraction {
    private int nominator;
    private int denominator;

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
        } else {
            result.denominator = lcm(a.denominator, b.denominator);
            result.nominator = (a.nominator * result.denominator/a.denominator)
                    + (b.nominator * (result.denominator/ b.denominator));
        }
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }

    public Fraction sum(Fraction b){
        Fraction result = new Fraction();
        if (this.denominator == b.denominator) {
            result.nominator = this.nominator + b.nominator;
            result.denominator = b.denominator;
        } else {
            result.denominator = lcm(this.nominator, b.denominator);
            result.nominator = (this.nominator * result.denominator/this.denominator)
                    + (b.nominator * (result.denominator/ b.denominator));
        }
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }

    public static Fraction substract(Fraction a, Fraction b) {
        Fraction result = new Fraction();
        if (a.denominator == b.denominator) {
            result.nominator = a.nominator - b.nominator;
            result.denominator = a.denominator;
        } else {
            result.denominator = lcm(a.denominator, b.denominator);
            result.nominator = (a.nominator * result.denominator/a.denominator)
                    - (b.nominator * (result.denominator/ b.denominator));
        }
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }

    public Fraction substract(Fraction b){
        Fraction result = new Fraction();
        if (this.denominator == b.denominator) {
            result.nominator = this.nominator - b.nominator;
            result.denominator = b.denominator;
        } else {
            result.denominator = lcm(this.nominator, b.denominator);
            result.nominator = (this.nominator * result.denominator/this.denominator)
                    - (b.nominator * (result.denominator/ b.denominator));
        }
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }


    public static Fraction multiply(Fraction a, Fraction b) {
        Fraction result = new Fraction();
        result.nominator = a.nominator * b.nominator;
        result.denominator = a.denominator * b.denominator;
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }

    public Fraction multiply(Fraction a) {
        Fraction result = new Fraction();
        result.nominator = this.nominator * a.nominator;
        result.denominator = this.denominator * a.denominator;
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }

    public static Fraction divide(Fraction a, Fraction b) throws Exception{
        Fraction result = new Fraction();
        if (a.nominator == 0) throw new Exception("Деление невозможно");
        result.nominator = a.nominator * b.denominator;
        result.denominator = a.denominator * b.nominator;
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator/gcd, result.denominator/gcd);
    }

    public Fraction divide(Fraction a) throws Exception{
        if (a.nominator == 0) throw new Exception("Деление невозможно");
        Fraction result = new Fraction();
        result.nominator = this.nominator * a.denominator;
        result.denominator = this.denominator * a.nominator;
        int gcd = gcd(result.nominator, result.denominator);
        return new Fraction(result.nominator / gcd, result.denominator / gcd);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
    public static int lcm(int a, int b) {
        int lcm = a * b / gcd(a, b);
        return lcm;
    }
}