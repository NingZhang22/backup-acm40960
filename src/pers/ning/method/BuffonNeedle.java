package pers.ning.method;

import pers.ning.calc.MonteCarlo;

// calculating PI using Buffon's Needle

public class BuffonNeedle extends MonteCarlo {

    public BuffonNeedle(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    public BuffonNeedle() {
        this("BuffonNeedle", new int[]{1309, 13090, 130900});
    }

    public double calc_pi(int n, double needle_length, double grid_size) {
        int m=0; // number of intersected needles
        double x, y1, y2; // coordinate of needle
        double angle; // angle of needle
        int i;

        for (i=0;i<n;i++) {
            x = this.rand.nextDouble()*grid_size;
            angle = Math.toRadians(this.rand.nextDouble()*180); // generate the random angle
            y1 = x + needle_length/2 * Math.sin(angle); // here the needle_length is treated as the two times of the length
            y2 = x - needle_length/2 * Math.sin(angle);

            if ((y2 < 0 && y1 >= 0) || (y1<0 && y2>=0)) {
                m++;
            }
        }

        return (needle_length * n) / (grid_size * m);
    }

    @Override
    public double calc_pi(int n) {
        return this.calc_pi(n, 2.0, 1.0);
    }

    public double calc_sigma(int i, double needle_length, double grid_size) {
        int n = this.n_list[i];
        double p = needle_length / this.pi_list[i] / grid_size;
        double var_p = p*(1-p)/n;
        double var_pi = needle_length*needle_length/(grid_size*grid_size)/(p*p)*var_p;
        return Math.sqrt(var_pi);
    }


    @Override
    public double calc_sigma(int i) {
        return this.calc_sigma(i, 2.0, 1.0);
    }
}
