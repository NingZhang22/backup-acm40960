package pers.ning.method;

import pers.ning.calc.Integral;

// calculating PI using integral method presented in (d)
// y = Math.sqrt(1-x*x)
// pi = 4.0*y_bar

public class IntegralD extends Integral {

    public IntegralD(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public IntegralD() {
        super("Integral-D");
    }

    @Override
    public double f(double x) {
        return Math.sqrt(1-x*x);
    }

    @Override
    public double pi(double y_bar) {
        return 4.0*y_bar;
    }
}
