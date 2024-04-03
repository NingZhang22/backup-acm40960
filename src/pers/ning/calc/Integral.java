package pers.ning.calc;

// calculating PI using integral

import java.util.Date;

public class Integral extends MonteCarlo{
    double[] x_list; // store the randomly generated x

    public Integral(String methodName, int[] n_list) {
        super(methodName, n_list);
        this.x_list = new double[this.max_n()];
    }

    public Integral(String methodName) {
        super(methodName);
        this.x_list = new double[this.max_n()];
    }

    // function of integral
    public double f(double x) {
        return 0.0;
    }

    // calculate PI according average of integral
    public double pi(double y_bar) {
        return 0.0;
    }

    @Override
    public double calc_pi(int n) {
        double sum=0.0;
        double x;
        int i;

        for (i=0;i<n;i++) {
            x = this.rand.nextDouble();
            this.x_list[i] = x;
            sum += this.f(x);
        }
        return this.pi(sum/n);
    }

    @Override
    public double calc_sigma(int i) {
        int n = this.n_list[i];
        double pi_bar = this.pi_list[i];
        double var_pi = 0.0;
        double x, y, pi;
        int j;

        for (j=0;j<n;j++) {
            x = this.x_list[j];
            y = this.f(x);
            pi = this.pi(y);
            var_pi += Math.pow(pi-pi_bar, 2);
        }
        var_pi /= n;
        return Math.sqrt(var_pi/n);
    }

    /*
    In integral method, random Xs are stored in x_list to calculate the sigma error in calc_pi().
    However, this might have an effect on the time consumption.
    calc_pi_timer() is to calculate pi without storing random x.
    Also, timer() is overrided here using calc_pi_timer() instead of calc_pi().
     */
    public double calc_pi_timer(int n) {
        double sum=0.0;
        double x;
        int i;

        for (i=0;i<n;i++) {
            x = this.rand.nextDouble();
            sum += this.f(x);
        }
        return this.pi(sum/n);
    }

    @Override
    public long timer(int n) {
        Date sdate = new Date(); // start time
        this.calc_pi_timer(n);
        Date edate = new Date(); // end time
        return edate.getTime() - sdate.getTime();
    }
}
