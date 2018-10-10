/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easygrid;

/**
 *
 * @author Jia Jia Chen
 */
//What does static mean? It means the thing is the same in all parts of a class.
//Try to make things private when possible to increase security and robustness.
public class Fraction {

    int num;
    int den;

    //Constructor 1 - Constructors are special; they don't have a return type because they return the class.
    public Fraction(int n, int d) {
        num = n;
        den = d;
    }

    public String reduce() {
        int low;
        int high;
        boolean negative = false;
        if (den > 0 && num < 0 || den < 0 && num > 0) {
            den = Math.abs(den);
            num = Math.abs(num);
            negative = true;
        } else if (den == 0) {
            num = 1;
            den = 1;
            return toString();
        }

        if (den < num) {
            low = den;
            high = num;
        } else if (num < den) {
            low = num;
            high = den;
        } else {
            low = num;
            high = num;
            num = num / low;
            den = den / low;
        }

        while (low > 0) {
            int div = 1;
            while (div * low < high) {
                div++;
            }
            if (div * low > high) {
                div--;
            }
            int low2 = low;
            low = high - (div * low);
            high = low2;
        }
        den = den / high;
        num = num / high;

        if (negative == true) {
            num = num * -1;
        }
        return toString();
    }

    private Fraction denMatch(Fraction other) {
        int num2 = other.num;
        int den2 = other.den;
        num2 = num2 * den;
        den2 = den2 * den;
        num = num * other.den;
        den = den * other.den;
        other.num = num2;
        other.den = den2;
        return other;
    }

    public String add(Fraction other) {
        other = denMatch(other);
        num = num + other.num;
        reduce();
        return toString();
    }

    public String subtract(Fraction other) {
        other = denMatch(other);
        num = num - other.num;
        reduce();
        return toString();
    }

    public String subtract(int n, int d) {
        Fraction other = new Fraction(n, d);
        other = denMatch(other);
        num = num - other.num;
        reduce();
        return toString();
    }

    @Override
    public String toString() {
        return num + " / " + den;
    }
}
