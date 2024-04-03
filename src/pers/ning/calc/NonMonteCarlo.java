package pers.ning.calc;

import java.awt.*;
import java.io.File;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

public class NonMonteCarlo extends CalcPI{

    public NonMonteCarlo(String methodName, int[] n_list) {
        super(methodName, n_list);
    }



    // plot the estimated value of PI versus log(N)
    @Override
    public int value_plot(String timestamp, int width, int height) {
        int i;

        // estimated PI
        final XYSeries series1 = new XYSeries("Estimated PI", false, true);

        for (i=0; i<this.n_count; i++) {
            series1.add(Math.log10(this.n_list[i]), this.pi_list[i]);
        }

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);


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
        plot.getRangeAxis().setRange(3.138, 3.143);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, Color.RED);

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
