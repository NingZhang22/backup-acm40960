package pers.ning.execution;

import pers.ning.calc.CalcPI;
import pers.ning.method.*;
import pers.ning.other.ComparisonPlot;

// compare the difference between estimated and actual PI among different methods

public class ComparingDiff {
    public static void main(String[] args) {
        int i, j;
        long seed;

        // create random seeds for Monte Carlo methods
        long[] seed_arr = {1,2,3};

        // create 8 objects for the 8 different methods
        CalcPI[] calc_arr = {
                new Archimedes(),
                new BuffonNeedle(),
                new QuarterCircle(),
                new IntegralD(),
                new IntegralE(),
                new IntegralF(),
                new Leibniz(),
                new EighthSphere()
        };

        // calculate PI using Archimedes methods
        calc_arr[0].calc_pi_list();

        // calculate differences between estimated and actual PI using different seeds
        for (i=0; i<seed_arr.length; i++) {
            seed = seed_arr[i];
            // calculate PIs and differences
            for (j=0; j<calc_arr.length; j++) {
                calc_arr[j].setSeed(seed);
                calc_arr[j].calc_pi_list();
            }
            // plot for the difference for each seed
            ComparisonPlot.compare_diff(calc_arr, "seed="+seed);
        }

    }
}
