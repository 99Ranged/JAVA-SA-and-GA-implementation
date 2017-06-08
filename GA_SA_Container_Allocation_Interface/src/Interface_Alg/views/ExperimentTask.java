package Interface_Alg.views;


import Interface_Alg.views.GraphPainter;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.Random;
import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ExperimentTask extends Thread
{
    
    int popSize;
    int tourSize;
    int generations;
    double CrossP;
    double MutateP;
    int ImageWidth;
    int ImageHeight;
    GUI gui;
    double[] data;
    
    
    public ExperimentTask(GUI gui, int popSize, int tourSize, int genNum, double[] weight, double CP, double MP, int ImageWidth, int ImageHeight) {
        this.popSize = popSize;
        this.tourSize = tourSize;
        generations = genNum;
        CrossP = CP;
        MutateP = MP;
        this.ImageWidth = ImageWidth;
        this.ImageHeight = ImageHeight;
        this.gui = gui;
        this.data = weight;
    }
    
    public void run() 
    {

    	GraphPainter GP = new GraphPainter();
        for (int i = 0; i < 10; i++) 
        {
            GP.AddGeneration(i,5, 5);
            GuiPainter PaintTask = new GuiPainter(gui, i);
            PaintTask.start();
        }
		




    }
    
}
