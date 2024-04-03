package pers.ning.test;

import pers.ning.method.*;

// this file is only for test work of estimation methods

public class MethodTest {
    public static void main(String[] args) {
        /*
        Archimedes archimedes = new Archimedes();
        archimedes.calc_pi_list();
        long timestamp = System.currentTimeMillis();
        archimedes.value_plot(Long.toString(timestamp));
        archimedes.diff_plot(Long.toString(timestamp));
        archimedes.time_plot(Long.toString(timestamp));
         */

        long timestamp = System.currentTimeMillis();
        String ts = Long.toString(timestamp);

        int i;

        BuffonNeedle mc = new BuffonNeedle();
        mc.calc_pi_list();
        mc.calc_sigma_list();

        for (i=0;i<mc.n_count;i++) {
            System.out.println(mc.pi_list[i]);
            System.out.println(mc.sigma_list[i]);
            System.out.println();
        }

        // please make sure you create the directories before storing the plots
        mc.value_plot(ts); // path: plot/single_method/(method_name)/value_plot/(file_name)
        mc.diff_plot(ts); // path: plot/single_method/(method_name)/diff_plot/(file_name)
        mc.time_plot(ts); // path: plot/single_method/(method_name)/time_plot/(file_name)

        /*
        BuffonNeedle bf = new BuffonNeedle();
        bf.calc_pi_list();
        bf.calc_sigma_list();

        for (i=0;i<bf.n_count;i++) {
            System.out.println(bf.sigma_list[i]);
        }

        bf.value_plot(ts);
        bf.diff_plot(ts);
        bf.time_plot(ts);

         */


        /*
        QuarterCircle qc = new QuarterCircle();
        qc.calc_pi_list();
        qc.calc_sigma_list();

        System.out.println(qc.minPI());
        System.out.println(qc.maxPI());

        qc.value_plot(ts);
        qc.diff_plot(ts);
        qc.time_plot(ts);

         */

    }
}
