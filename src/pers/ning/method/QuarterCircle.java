package pers.ning.method;

import pers.ning.calc.MonteCarlo;

// calculating PI using quarter circle

public class QuarterCircle extends MonteCarlo {
    public QuarterCircle(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public QuarterCircle() {
        super("QuarterCircle");
    }

    @Override
    public double calc_pi(int n) {
        int m=0; // number of point inside the quarter circle
        double x, y; // coordinate of point
        int i;

        for (i=0;i<n;i++) {
            x = this.rand.nextDouble();
            y = this.rand.nextDouble();
            if (x*x+y*y<=1) {
                m++;
            }
        }

        return 4.0*(double)m / (double)n;
    }

    @Override
    public double calc_sigma(int i) {
        int n = this.n_list[i];
        double p = this.pi_list[i]  / 4.0;
        double var_p = p*(1-p);
        double var_pi = 16*var_p;
        return Math.sqrt(var_pi/n);
    }
}
