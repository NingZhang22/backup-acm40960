package pers.ning.other;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pers.ning.calc.CalcPI;

import java.io.File;

public class ComparisonPlot {
    // plot for the comparison of differences between estimated and actual PI for different methods
    public static int compare_diff(CalcPI[] calc_arr, String ts, int width, int height) {
        int n = calc_arr.length; // length of method array
        int i, j;
        XYSeries series;

        final XYSeriesCollection data = new XYSeriesCollection();
        for (i=0;i<n;i++) {
            series = new XYSeries(calc_arr[i].methodName, false, true);
            for (j=0;j<calc_arr[i].n_count;j++) {
                series.add(Math.log10(calc_arr[i].n_list[j]), Math.abs(calc_arr[i].pi_list[j]-Math.PI));
            }
            data.addSeries(series);
        }

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Differences Between Estimated and Actual PI for Different Methods - " + ts,
                "log(N)",
                "Difference",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        String path = "plot/comparison/comparison_diff_" + ts + ".jpeg";
        try {
            File file = new File( path );
            ChartUtilities.saveChartAsJPEG( file, chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static int compare_diff(CalcPI[] calc_arr, String ts) {
        return ComparisonPlot.compare_diff(calc_arr, ts, 640, 480);
    }

    // plot for the comparison of time consumption between different methods
    public static int compare_time(CalcPI[] calc_arr, String ts, int width, int height) {
        int n = calc_arr.length; // length of method array
        int n1;
        int i, j;
        XYSeries series;

        final XYSeriesCollection data = new XYSeriesCollection();
        for (i=0;i<n;i++) {
            series = new XYSeries(calc_arr[i].methodName, false, true);
            for (j=0;j<calc_arr[i].n_count;j++) {
                n1 = calc_arr[i].n_list[j];
                series.add(Math.log10(n1), calc_arr[i].timer(n1));
            }
            data.addSeries(series);
        }

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Time Consumption of Different Methods - " + ts,
                "log(N)",
                "time consumption (ms)",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        String path = "plot/comparison/comparison_time_" + ts + ".jpeg";
        try {
            File file = new File( path );
            ChartUtilities.saveChartAsJPEG( file, chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static int compare_time(CalcPI[] calc_arr, String ts) {
        return ComparisonPlot.compare_time(calc_arr, ts, 640, 480);
    }
}
