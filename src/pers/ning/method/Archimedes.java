package pers.ning.method;

import pers.ning.calc.NonMonteCarlo;

// calculating PI using Archimedes methods

public class Archimedes extends NonMonteCarlo {
    public Archimedes(String methodName, int[] n_list) {
        super(methodName, n_list);
    }

    // {64, 1024, 16384, 131072} are the numbers of sides
    public Archimedes() {
        this("Archimedes", new int[]{64, 1024, 16384, 131072});
    }

    @Override
    public double calc_pi(int n) {
        double polygon_edge_length_squared = 2.0;
        int polygon_sides = 4;
        int niter = (int)(Math.log(n/polygon_sides) / Math.log(2));
        int i;
        for (i=0;i<niter;i++) {
            polygon_edge_length_squared = 2 - 2 * Math.sqrt(1 - polygon_edge_length_squared / 4);
            polygon_sides *= 2;
        }
        return polygon_sides * Math.sqrt(polygon_edge_length_squared) / 2;
    }
}
