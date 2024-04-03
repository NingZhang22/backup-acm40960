package pers.ning.execution;

import pers.ning.calc.CalcPI;
import pers.ning.method.*;
import pers.ning.other.ComparisonPlot;

public class ComparingTime {
    public static void main(String[] args) {
        // create timestamp
        String ts = Long.toString(System.currentTimeMillis());

        // n_list for Archimedes method
        int[] n_arr1 = {64, 1024, 16384, 131072, 1048576};
        // n_list for integral methods
        int[] n_arr2 = {100, 1000, 10000, 100000, 1000000};

        // create the array for different methods
        // as archimedes, Integral-D and Integral-F have relatively high accuracy and good convergence
        // choose these 3 methods and compare the time consumption
        CalcPI[] calc_arr = {
                new Archimedes("Archimedes", n_arr1),
                new IntegralD("Integral-D", n_arr2),
                new IntegralF("Integral-F", n_arr2)
        };

        // plot for the time consumption
        ComparisonPlot.compare_time(calc_arr, ts);
    }
}
