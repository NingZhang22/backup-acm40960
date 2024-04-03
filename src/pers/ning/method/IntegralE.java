package pers.ning.method;


import pers.ning.calc.Integral;

// calculating PI using integral method presented in (e)
// y = Math.pow(Math.E, 1-1/x) / (x*x*Math.sqrt(1/x-1))
// pi = y_bar*y_bar

public class IntegralE extends Integral {

    public IntegralE(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public IntegralE() {
        super("Integral-E");
    }

    @Override
    public double f(double x) {
        return Math.pow(Math.E, 1-1/x) / (x*x*Math.sqrt(1/x-1));
    }

    @Override
    public double pi(double y_bar) {
        return y_bar*y_bar;
    }
}
