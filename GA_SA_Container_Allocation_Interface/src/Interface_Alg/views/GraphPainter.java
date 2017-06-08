package Interface_Alg.views;


import java.awt.image.BufferedImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphPainter
{
    
    XYSeries BestSeries;
    XYSeries AvgSeries;
    
    public GraphPainter()
    {
	AvgSeries = new XYSeries("Average Fitness");
	BestSeries = new XYSeries("Best Series");
    }
    
    public void AddGeneration(int generation, double solution, double fitness)
    {
	AvgSeries.add(generation,solution);
	BestSeries.add(generation, fitness);
    }
    
    public BufferedImage Paint(String GraphTitle, int width, int height)
    {
	XYSeriesCollection DS = new XYSeriesCollection();
	DS.addSeries(AvgSeries);
	DS.addSeries(BestSeries);
	JFreeChart chart = ChartFactory.createXYLineChart(
                      GraphTitle,		    // Title
                      "Generation",		    // X-Axis label
                      "Fitness",		    // Y-Axis label
                      DS,			    // Dataset
                      PlotOrientation.VERTICAL,	    //plot orientation
		      true, false, false);
	return chart.createBufferedImage(width, height);
    }
    
    
}
