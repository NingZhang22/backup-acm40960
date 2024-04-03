package pers.ning.calc;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.util.Date;

public class CalcPI {
    public String methodName;
    public int[] n_list; // an array of Ns (e.g. {100, 1000, 10000, 100000})
    public int n_count;
    public double[] pi_list; // store the estimated PI for different N

    public CalcPI(String methodName, int[] n_list) {
        this.methodName = methodName;
        this.n_list = n_list;
        this.n_count = n_list.length;
        this.pi_list = new double[this.n_count];
    }

    @Override
    public String toString() {
        return this.methodName;
    }

    public double calc_pi(int n) {
        return 0.0;
    }

    // store the results of estimation into pi_list
    public void calc_pi_list() {
        int i;
        for (i=0; i<this.n_count; i++) this.pi_list[i] = calc_pi(this.n_list[i]);
    }

    // calculate the time consumption of estimating PI once (in ms)
    public long timer(int n) {
        Date sdate = new Date(); // start time
        this.calc_pi(n);
        Date edate = new Date(); // end time
        return edate.getTime() - sdate.getTime();
    }

    // plot the time consumption versus log(N)
    public int time_plot(String timestamp, int width, int height) {
        int i;

        final XYSeries series1 = new XYSeries("Time Consumption", false, true);
        for (i=0; i<this.n_count; i++) {
            series1.add(Math.log10(this.n_list[i]), this.timer(this.n_list[i]));
        }

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                this.methodName + ": time consumption versus log(N)",
                "log(N)",
                "Time Consumption (ms)",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();


        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);

        String path = "plot/single_method/" + this.methodName + "/time_plot/" + this.methodName + " - " + timestamp + ".jpeg";
        try {
            File file = new File( path );
            ChartUtilities.saveChartAsJPEG( file, chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    public int time_plot(String timestamp) {
        return this.time_plot(timestamp, 640, 480);
    }

    // plot the absolute difference between estimated PI and actual value versus log(N)
    public int diff_plot(String timestamp, int width, int height) {
        int i;

        final XYSeries series1 = new XYSeries("Absolute Difference", false, true);
        for (i=0; i<this.n_count; i++) {
            series1.add(Math.log10(this.n_list[i]), Math.abs(this.pi_list[i]-Math.PI));
        }

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                this.methodName + ": Absolute Difference versus log(N)",
                "log(N)",
                "Absolute Difference",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);

        String path = "plot/single_method/" + this.methodName + "/diff_plot/" + this.methodName + " - " + timestamp + ".jpeg";
        try {
            File file = new File( path );
            ChartUtilities.saveChartAsJPEG( file, chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    public int diff_plot(String timestamp) {
        return this.diff_plot(timestamp, 640, 480);
    }

    // plot the estimated value of PI and actual value versus log(N)
    public int value_plot(String timestamp, int width, int height) {
        return 0;
    }
    public int value_plot(String timestamp) {
        return this.value_plot(timestamp, 640, 480);
    }

    // change random seed
    public void setSeed(long seed) {
        return ;
    }

    // return the minimum estimated PI
    public double minPI() {
        double mp = this.pi_list[0];
        int i;
        for (i=1;i<this.n_count;i++) {
            if (mp>this.pi_list[i]) {
                mp = this.pi_list[i];
            }
        }
        return mp;
    }

    // return the maximum estimated PI
    public double maxPI() {
        double mp = this.pi_list[0];
        int i;
        for (i=1;i<this.n_count;i++) {
            if (mp<this.pi_list[i]) {
                mp = this.pi_list[i];
            }
        }
        return mp;
    }

    // return the maximum n
    public int max_n() {
        int mn = this.n_list[this.n_count-1];
        int i;
        for (i=0;i<this.n_count-1;i++) {
            if (mn<this.n_list[i]) {
                mn = this.n_list[i];
            }
        }
        return mn;
    }

}
