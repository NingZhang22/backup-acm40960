package pers.ning.execution;

import pers.ning.calc.MonteCarlo;
import pers.ning.method.*;

// estimate PI and error bars using eight methods

public class EstimatingPI {
    public static void main(String[] args) {
        int i, j;
        long seed;

        // create random seeds for Monte Carlo methods
        long[] seed_arr = {1,2,3};

        // create timestamp
        String ts = Long.toString(System.currentTimeMillis());

        // estimate PI using the first method (Archimedes)
        Archimedes archimedes = new Archimedes();
        archimedes.calc_pi_list();
        archimedes.value_plot(ts);

        // create 7 objects for the rest of 7 different methods
        MonteCarlo[] mc_arr = {
                new BuffonNeedle(),
                new QuarterCircle(),
                new IntegralD(),
                new IntegralE(),
                new IntegralF(),
                new Leibniz(),
                new EighthSphere()
        };

        // calculatee PI and error bars using the rest methods
        for (i=0; i<seed_arr.length; i++) {
            seed = seed_arr[i];
            for (j=0; j<mc_arr.length; j++) {
                mc_arr[j].setSeed(seed);
                mc_arr[j].calc_pi_list();
                mc_arr[j].calc_sigma_list();
                mc_arr[j].value_plot(ts+"(seed="+seed+")");
            }
        }

    }
}
