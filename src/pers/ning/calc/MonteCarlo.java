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
import java.util.Random;

public class MonteCarlo extends CalcPI{
    public double[] sigma_list; // store the sigma for different N
    public Random rand; // random object

    public MonteCarlo(String methodName, int[] n_list) {
        super(methodName, n_list);
        this.sigma_list = new double[this.n_count];
        this.rand = new Random();
    }
    public MonteCarlo(String methodName) {
        this(methodName, new int[]{100, 1000, 10000, 100000});
    }

    // change random seed
    @Override
    public void setSeed(long seed) {
        this.rand = new Random(seed);
    }

    // calculate the sigma according to the index i
    public double calc_sigma(int i) {
        return 0.0;
    }

    public void calc_sigma_list() {
        int i;
        for (i=0; i<this.n_count; i++) this.sigma_list[i] = calc_sigma(i);
    }

    // plot the estimated value of PI versus log(N)
    @Override
    public int value_plot(String timestamp, int width, int height) {
        int i;
        int n;
        double logn, pi, sigma_error;


        // estimated PI
        final XYSeries series1 = new XYSeries("Estimated PI", false, true);
        // upper of 2 sigma error
        final XYSeries series2 = new XYSeries("Upper of 2 Sigma Error", false, true);
        // upper of 1 sigma error
        final XYSeries series3 = new XYSeries("Upper of 1 Sigma Error", false, true);
        // lower of 1 sigma error
        final XYSeries series4 = new XYSeries("Lower of 1 Sigma Error", false, true);
        // lower of 2 sigma error
        final XYSeries series5 = new XYSeries("Lower of 2 Sigma Error", false, true);

        for (i=0; i<this.n_count; i++) {
            n = this.n_list[i];
            logn = Math.log10(n);
            pi = this.pi_list[i];
            sigma_error = this.sigma_list[i];
            series1.add(logn, pi);
            series2.add(logn, pi+2*sigma_error);
            series3.add(logn, pi+sigma_error);
            series4.add(logn, pi-sigma_error);
            series5.add(logn, pi-2*sigma_error);
        }

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);
        data.addSeries(series2);
        data.addSeries(series3);
        data.addSeries(series4);
        data.addSeries(series5);


        final JFreeChart chart = ChartFactory.createXYLineChart(
                this.methodName + ": Estimated PI versus log(N)",
                "log(N)",
                "Estimated PI",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        // set the range of y-axis
        plot.getRangeAxis().setRange(this.minPI()-0.05, this.maxPI()+0.05);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);

        // solid curve
        renderer.setSeriesStroke(0, new BasicStroke(2));
        // dashed curve
        for (i=1;i<5;i++) {
            renderer.setSeriesStroke(i, new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, new float[]{10f, 6f}, 0.0f));
        }

        // set the colors
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.CYAN);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.GREEN);
        renderer.setSeriesPaint(4, Color.CYAN);
        plot.setRenderer(renderer);

        String path = "plot/single_method/" + this.methodName + "/value_plot/" + this.methodName + "-" + timestamp + ".jpeg";
        try {
            File file = new File( path );
            ChartUtilities.saveChartAsJPEG( file, chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
