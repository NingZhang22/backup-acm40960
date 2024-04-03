package pers.ning.method;

import pers.ning.calc.MonteCarlo;

// calculating PI using an eighth of a sphere

public class EighthSphere extends MonteCarlo {
    public EighthSphere(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public EighthSphere() {
        super("EighthSphere");
    }

    @Override
    public double calc_pi(int n) {
        int m=0; // number of point inside the eighth of sphere
        double x, y, z; // coordinate of point
        int i;

        for (i=0;i<n;i++) {
            x = this.rand.nextDouble();
            y = this.rand.nextDouble();
            z = this.rand.nextDouble();
            if (x*x+y*y+z*z<=1) {
                m++;
            }
        }

        return 6.0*(double)m / (double)n;
    }

    @Override
    public double calc_sigma(int i) {
        int n = this.n_list[i];
        double p = this.pi_list[i]  / 6.0;
        double var_p = p*(1-p);
        double var_pi = 36*var_p;
        return Math.sqrt(var_pi/n);
    }
}
