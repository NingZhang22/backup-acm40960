package pers.ning.method;

import pers.ning.calc.MonteCarlo;

// calculating PI using Leibniz formula

public class Leibniz extends MonteCarlo {
    public Leibniz(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public Leibniz() {
        super("Leibniz");
    }

    // returns the closest integer of a double
    int close_int(double lf) {
        int lower = (int)lf;
        int upper = lower+1;
        if ((lf-lower) < (upper-lf)) {
            return lower;
        }
        return upper;
    }

    @Override
    public double calc_pi(int n) {
        int m = 0; // number of even closest integer
        double x, y;
        double p;
        int i;

        for (i=0;i<n;i++) {
            x = this.rand.nextDouble();
            y = this.rand.nextDouble();
            if (this.close_int(x/y)%2==0) {
                m++;
            }
        }

        p = (double)m / (double)n;
        return 5.0-4.0*p;
    }

    @Override
    public double calc_sigma(int i) {
        int n = this.n_list[i];
        double p = (5.0-this.pi_list[i]) / 4.0;
        double var_p = p*(1-p);
        double var_pi = 16*var_p;
        return Math.sqrt(var_pi/n);
    }
}
