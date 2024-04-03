package pers.ning.method;

import pers.ning.calc.Integral;

// calculating PI using integral method presented in (f)
// y = (1/x-1) / (x*x*(Math.pow(Math.E, 1/x-1)-1))
// pi = Math.sqrt(y_bar*6)

public class IntegralF extends Integral {

    public IntegralF(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public IntegralF() {
        super("Integral-F");
    }

    @Override
    public double f(double x) {
        return (1/x-1) / (x*x*(Math.pow(Math.E, 1/x-1)-1));
    }

    @Override
    public double pi(double y_bar) {
        return Math.sqrt(y_bar*6);
    }
}
