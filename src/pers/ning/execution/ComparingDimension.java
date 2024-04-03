package pers.ning.execution;

import pers.ning.calc.CalcPI;
import pers.ning.calc.MonteCarlo;
import pers.ning.method.*;
import pers.ning.other.ComparisonPlot;

// This is to compare the methods of Quarter Circle in 2D and Eighth Sphere in 3D

public class ComparingDimension {
    public static void main(String[] args) {
        int i, j;
        long seed;

        // create random seeds for Monte Carlo methods
        long[] seed_arr = {1,2,3};
        // n_list for the 2 methods
        int[] n_arr = {100, 1000, 10000, 100000, 1000000};

        // create 2 objects for the 2 methods
        MonteCarlo[] calc_arr = {
                new QuarterCircle("QuarterCircle", n_arr),
                new EighthSphere("EighthSphere", n_arr)
        };

        // calculate differences between estimated and actual PI using different seeds
        for (i=0; i<seed_arr.length; i++) {
            seed = seed_arr[i];
            // calculate PIs and differences
            for (j=0; j<calc_arr.length; j++) {
                calc_arr[j].setSeed(seed);
                calc_arr[j].calc_pi_list();
            }
            // plot for the difference for each seed
            ComparisonPlot.compare_diff(calc_arr, "seed"+seed);
        }

        String ts = "2Dvs3D";
        // plot for the time consumption
        ComparisonPlot.compare_time(calc_arr, ts);

    }
}
