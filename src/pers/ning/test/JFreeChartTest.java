package pers.ning.test;

import java.io.File;

import org.jfree.chart.ChartUtilities;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

// Plotting in Java requires jfreechart package.
// This file is to check if the package works.


public class JFreeChartTest {
    public static void main(String[] args) throws Exception{
        final XYSeries series1 = new XYSeries("Data 1", false, true);
        series1.add( 1.0 , 4.0 );
        series1.add( 2.0 , 5.0 );

        final XYSeries series2 = new XYSeries("Data 2", false, true);
        series2.add( 1.0 , 4.0 );
        series2.add( 2.5 , 7.0 );
        series2.add( 4.0 , 6.5 );

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);
        data.addSeries(series2);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Chart",
                "X",
                "Y",
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

        File file = new File( "plot/test/test.jpeg" );
        ChartUtilities.saveChartAsJPEG( file, chart, 640, 480);
    }

}
