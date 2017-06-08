package Interface_Alg.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import Interface_Alg.views.GuiPainter;

import Interface_Alg.views.GraphPainter;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.RenderingHints;


public class GUI extends JFrame {

	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel GeneticPanel;
	private JPanel SimulatedPanel;
	private BufferedWriter originaltxt; 
	private JFormattedTextField PopSizeField;
	private JTextField DirectField;
	private JTextField DirectFieldSA;
	private JFormattedTextField MutationField;
	private JFormattedTextField CapacityField;
	private JFormattedTextField CapacityFieldSA;
	private JFormattedTextField GenNumField;
	private JFormattedTextField CrossoverField;
	private JTextField Weights;
	private JTextField PopSizeField2;
	private JTextField MutationField2;
	private JFormattedTextField TournamentField;
	private JTextField CrossoverField2;
	private JTextField TournamentField2;
	private JFormattedTextField NeiSAField;
	private JFormattedTextField NumIterSAField;
	private JFormattedTextField ControlFSAField;
	private JFormattedTextField ControlPSAField;
	private javax.swing.JProgressBar ProgressBar;
	private BufferedImage BI;
	
	GUI gui;

	
	private String pathDirectory;
	private Object[][] tableData;
	private Object[][] tableDataSA;

	private File selectedFile;
	
	private double[] weights;// = new double[24];
	private String[] columnNames = {"Generation",
									"Fitness",
									"Solution"};
	private String[] columnNamesSA = {"Iteration",
			"Fitness",
			"Solution"};
	
	private int counter = 0;
	private JTable table;
	private JTable tableSA;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					GUI frame = new GUI();
					frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Interface_Alg/resources/algoIcon.png")));
		
		initComponents();
		

		
	}


	/***********This method contains all of the code for creating and initialize components***************/
	private void initComponents() {
		
		
		setTitle("Algorithm Interface");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel panelMenu = new JPanel();
		
		panelMenu.setPreferredSize(new Dimension(800,200));
		panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelMenu, "MenuPanel");
		
		
		
		panelMenu.setVisible(true);
		;
		
		JPanel GeneticPanel = new JPanel();
		contentPane.add(GeneticPanel, "Genetic");
		GeneticPanel.setLayout(null);
		GeneticPanel.setVisible(false);
		 
		
		JLabel lblGeneticAlgortithm = new JLabel("Genetic Algortithm");
		lblGeneticAlgortithm.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneticAlgortithm.setBounds(10, 8, 110, 20);
		GeneticPanel.add(lblGeneticAlgortithm);
		
		Button openFileButton = new Button("Import");
		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
			    int returnValue = fileChooser.showOpenDialog(null);
			    if (returnValue == JFileChooser.APPROVE_OPTION)
			    {
			        selectedFile = fileChooser.getSelectedFile();
			        pathDirectory = selectedFile.toString();
			        fileChooser.setCurrentDirectory(new File("C:/"));
			        DirectField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			    }
			    
			    
				
			
			}	
		});
		openFileButton.setBounds(298, 71, 70, 22);
		GeneticPanel.add(openFileButton);
		
		JLabel lblParameters = new JLabel("Parameters");
		lblParameters.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblParameters.setHorizontalAlignment(SwingConstants.LEFT);
		lblParameters.setBounds(20, 42, 70, 14);
		GeneticPanel.add(lblParameters);
		
		JLabel lblWeigths = new JLabel("Weigths");
		lblWeigths.setBounds(20, 71, 70, 22);
		GeneticPanel.add(lblWeigths);
		/*
		 *---------------------------- create a number formatter for all other inputs
		 */
		NumberFormat format = NumberFormat.getInstance();
	    format.setGroupingUsed(false);
	    
		NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);

	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	
	    
		JLabel lblCapacity = new JLabel("Capacity:");
		lblCapacity.setBounds(20, 104, 89, 22);
		GeneticPanel.add(lblCapacity);
		
		CapacityField = new JFormattedTextField(formatter);
		
		CapacityField.setBounds(124+50, 104, 38, 20);
		GeneticPanel.add(CapacityField);
		CapacityField.setColumns(10);
		
		JLabel lblGenNum = new JLabel("Number of generations:");
		lblGenNum.setBounds(20, 135, 89+50, 22);
		GeneticPanel.add(lblGenNum);
		
		GenNumField = new JFormattedTextField(formatter);
		GenNumField.setBounds(124+50, 135, 38, 20);
		GeneticPanel.add(GenNumField);
		GenNumField.setColumns(10);
		
		JLabel lblPopulationSize = new JLabel("Population Size:");
		lblPopulationSize.setBounds(20, 135+35, 89+50, 22);
		GeneticPanel.add(lblPopulationSize);
		
		PopSizeField = new JFormattedTextField(formatter);
		PopSizeField.setBounds(124+50, 135+35, 38, 20);
		GeneticPanel.add(PopSizeField);
		PopSizeField.setColumns(10);
		
		
		
		
		DirectField = new JTextField();
		DirectField.setBounds(124, 71, 168, 20);
		GeneticPanel.add(DirectField);
		DirectField.setColumns(10);
		
		JLabel lblMutationRate = new JLabel("Mutation Rate");
		lblMutationRate.setBounds(20, 137+35+35, 100, 14);
		GeneticPanel.add(lblMutationRate);
		
		
		/*
		 * 
		 * Set the decimal formatter for the mutation rate slider
		 */
		 DecimalFormat dc = new DecimalFormat("0.00");
		 dc.setMinimumFractionDigits(2);
		 dc.setMaximumFractionDigits(2);
		 dc.setRoundingMode(RoundingMode.HALF_UP);
		 
		 /*
			 * 
			 * Define the mutation field as a formatted text field, to accept decimal values
			 */
		MutationField = new JFormattedTextField(dc);
		MutationField.setBounds(124+50, 137+35+35, 38, 20);
		MutationField.setColumns(10);
		MutationField.setEditable(false);
		GeneticPanel.add(MutationField);
		
		 /*
		 * 
		 * Create the slider, ranging from 0 to 100, with 1 unit intervals - to be converted to decimal
		 */
		JSlider MutationSlider_ga = new JSlider(0, 100, 1);
		MutationSlider_ga.setPaintTicks(true);
		MutationSlider_ga.setMajorTickSpacing(10);
		MutationSlider_ga.setMinorTickSpacing(5);
		
		/*
		 * Set the labels for the slider, to give input
		 */
		Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		        position.put(0, new JLabel("0"));
		        position.put(25, new JLabel("0.25"));
		        position.put(50, new JLabel("0.50"));
		        position.put(75, new JLabel("0.75"));
		        position.put(100, new JLabel("1"));

		/*
		 * Activate a change listener for the slider, so it updates the value to give visual feedback
		*/		        
		   MutationSlider_ga.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				double v = (double) MutationSlider_ga.getValue()/100;
				dc.format(v);
				MutationField.setValue(v);
				
			}
			 
			 
		 });
		 
		 /*
		  * Set the slider position, activiate the labels and add it to the GUI
		*/		 
		  MutationSlider_ga.setLabelTable(position);
		  MutationSlider_ga.setPaintLabels(true);
		  MutationSlider_ga.setBounds(20, 101+130, 200, 90);
		GeneticPanel.add(MutationSlider_ga);
		
		
		
		
		JLabel lblCrossover = new JLabel("Crossover Rate");
		lblCrossover.setBounds(20, 325, 100, 20);
		GeneticPanel.add(lblCrossover);
		
		CrossoverField = new JFormattedTextField();
		CrossoverField.setBounds(124+50, 325, 38, 20);
		GeneticPanel.add(CrossoverField);
		CrossoverField.setColumns(10);
		CrossoverField.setEditable(false);
		
		
		
		/*
		 * CROSS OVER SLIDER ------------------------------------------------------------------------
		 * Set the decimal formatter for the mutation rate slider


		 /*
		 * 
		 * Create the slider, ranging from 0 to 100, with 1 unit intervals - to be converted to decimal
		 */
		JSlider CrossoverSlider_ga = new JSlider(0, 100, 1);
		CrossoverSlider_ga.setPaintTicks(true);
		CrossoverSlider_ga.setMajorTickSpacing(10);
		CrossoverSlider_ga.setMinorTickSpacing(5);
		
	        
		CrossoverSlider_ga.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				double v = (double) CrossoverSlider_ga.getValue()/100;
				dc.format(v);
				CrossoverField.setValue(v);
				
			}
			 
			 
		 });
		 
		 /*
		  * Set the slider position, activiate the labels and add it to the GUI
		*/		 
		   CrossoverSlider_ga.setLabelTable(position);
		   CrossoverSlider_ga.setPaintLabels(true);
		   CrossoverSlider_ga.setBounds(20, 315+30, 200, 90);
		GeneticPanel.add(CrossoverSlider_ga);
		
		
		
		
		
		PopSizeField.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancel");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMenu.setVisible(true);
				GeneticPanel.setVisible(false);
			}
		});
		btnCancelar.setBounds(20, 353+200, 89, 23);
		GeneticPanel.add(btnCancelar);
		
		JLabel lblTourn = new JLabel("Tournament Size");
		lblTourn.setBounds(20, 199+120+20+108, 110, 14);
		GeneticPanel.add(lblTourn);
		
		TournamentField = new JFormattedTextField(formatter);
		TournamentField.setBounds(124+50, 199+120+20+108, 38, 20);
		GeneticPanel.add(TournamentField);
		TournamentField.setColumns(10);
		
		JLabel lblselectionmethod = new JLabel("Selection method");
		lblselectionmethod.setBounds(20, 199+120+20+108+34, 110, 14);
		GeneticPanel.add(lblselectionmethod);
		
		String[] selecStrings = { "Tournament", "Ranking", "Roulette" };

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox selectionmethodList = new JComboBox(selecStrings);
		selectionmethodList.setSelectedIndex(0);
		selectionmethodList.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Parameters.selectionmethod = (String) selectionmethodList.getSelectedItem();
		    	
		    }
		});
		selectionmethodList.setBounds(124+50, 199+120+20+108+34, 38+90, 20);
		GeneticPanel.add(selectionmethodList);
		
		
		
		XYSeries series = new XYSeries("Genetic Algorithm");
	
		JTextArea ResultField = new JTextArea();
		ResultField.setToolTipText("S");
		ResultField.setText("Test");
		ResultField.setBounds(709, 42, -285, 236);
		GeneticPanel.add(ResultField);
		
		
		JPanel Table = new JPanel();		
		table = new JTable(tableData, columnNames);
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		table.setModel(dtm);
		String header[] = new String[] { "Generation", "Fitness", "Structure"
	          };

		// add header in table model     
		dtm.setColumnIdentifiers(header);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Scanner sc2 = null;
				try{
					sc2 = new Scanner(new File(pathDirectory));
				} catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
				while(sc2.hasNextLine())
				{
					Scanner s2 =  new Scanner(sc2.nextLine());
					while(s2.hasNext())
					{
						
						String s = s2.next();
						
						
						
						counter++;
					}
					s2.close();
					
				}
				
				weights = new double[counter];
				counter = 0;
				try{
					sc2 = new Scanner(new File(pathDirectory));
				} catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
				while(sc2.hasNextLine())
				{
					Scanner s2 =  new Scanner(sc2.nextLine());
					while(s2.hasNext())
					{
						
						String s = s2.next();
						
						
						weights[counter] = Double.parseDouble(s);
						counter++;
					}
					s2.close();
					
				}
				
				
				
				sc2.close();
				counter = 0;
				
				if(btnRun.isEnabled())  // AQUI VAMOS ATIVAR O BOTÃO RUN QUE IRÁ FAZER TRIGGER DA CLASSE EXPERIMENT TASK ONDE ESTARA IMPLEMENTADO O ALGORITMO
		        {
					System.out.println("pop: " + Integer.valueOf(PopSizeField.getText()));
					int popSize = Integer.valueOf(PopSizeField.getText());
		            int NumGenerations = Integer.valueOf(GenNumField.getText());
		            String MRate = MutationField.getText().replaceAll(",",".");
		            String CRate = CrossoverField.getText().replaceAll(",",".");
		            System.out.println("mutation rate:" + MRate);
		            System.out.println("crossover rate:" + CRate);
		            double crossoverRate = Double.valueOf(CRate);
		            double mutationRate = Double.valueOf(MRate);
		            int tournamentSize = Integer.valueOf(TournamentField.getText());
		            double capacity = Double.valueOf(CapacityField.getText());
		            boolean Maximization = false;
		            double inputs[] = weights;
		            boolean elitism = true;
		            int stringhLength = weights.length;
		           
	            	
		            GeneticAlgorithm R = new GeneticAlgorithm(this, stringhLength,capacity, Maximization ,elitism ,popSize, tournamentSize, NumGenerations, inputs, crossoverRate, mutationRate,700, 420);
		            R.Run();
		            
		            //Clean the table
		            for(int i = 0; i <R.numfitnessevaluationsX.length;i++)
		            {
		            
		            	
		            	
		            }
		            dtm.setRowCount(0);
		            
		            for(int i = 0; i <R.numfitnessevaluationsX.length;i++)
		            {
		            	series.addOrUpdate(R.numfitnessevaluationsX[i], R.fitnessesY[i]);
		            	
		            	dtm.addRow(new Object[] { R.numfitnessevaluationsX[i], R.fitnessesY[i], R.structureArray[i]});
		            	
		            }
		            
		        }
				
			}
				   
		});
		btnRun.setBounds(20, 275+240, 89, 23);
		GeneticPanel.add(btnRun);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(374, 5, 700, 516);
		GeneticPanel.add(tabbedPane);
		
				
		

		
		
		JScrollPane tablePane = new JScrollPane(table);
		tabbedPane.addTab("Table", null, tablePane, null);
		
		
		
		/* ***************** GRAFICO **************/

		
		GraphPainter GP = new GraphPainter();
       /* for (int i = 0; i < 10; i++) 
        {
           // GP.AddGeneration(i,1, 120);
            GuiPainter PaintTask = new GuiPainter(this, i);
            PaintTask.start();
        }
*/
		
        
       
       

     // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

     // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
        "Genetic Algorithm", // Title
        "Generations", // x-axis Label
        "Best fitness", // y-axis Label
        dataset, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        	);
        
        JPanel graphPanel = new JPanel();
		tabbedPane.addTab("Graph", null, graphPanel, null);
		
		ChartPanel CP = new ChartPanel(chart);

		graphPanel.add(CP,BorderLayout.CENTER);
		graphPanel.validate();
		
		



		JPanel SimulatedPanel = new JPanel();
		contentPane.add(SimulatedPanel, "name_56118183532390");
		SimulatedPanel.setLayout(null);
		
		
		JLabel lblSimulatedAnnealing = new JLabel("Simulated Annealing");
		lblSimulatedAnnealing.setBounds(10, 11, 131, 14);
		SimulatedPanel.add(lblSimulatedAnnealing);
		
		JLabel lblParameters_1 = new JLabel("Parameters");
		lblParameters_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblParameters_1.setBounds(20, 36, 80, 14);
		SimulatedPanel.add(lblParameters_1);
		
		JLabel lblWeights = new JLabel("Weights");
		lblWeights.setBounds(23, 73, 77, 14);
		SimulatedPanel.add(lblWeights);
		
		Weights = new JTextField();
		Weights.setBounds(125, 70, 165, 20);
		SimulatedPanel.add(Weights);
		Weights.setColumns(10);
		
	    
		JLabel lblCapacitySA = new JLabel("Capacity:");
		lblCapacitySA.setBounds(23, 104, 89+30, 22);
		SimulatedPanel.add(lblCapacitySA);
		
		CapacityFieldSA = new JFormattedTextField(formatter);
		
		CapacityFieldSA.setBounds(124+53, 104, 38+30, 20);
		SimulatedPanel.add(CapacityFieldSA);
		CapacityFieldSA.setColumns(10);
		
		JLabel lblIteSA = new JLabel("Iterations:");
		lblIteSA.setBounds(23, 104+32, 89, 22);
		SimulatedPanel.add(lblIteSA);
		
		NumIterSAField = new JFormattedTextField(formatter);
		
		NumIterSAField.setBounds(124+53, 104+32, 38+30, 20);
		SimulatedPanel.add(NumIterSAField);
		NumIterSAField.setColumns(10);
		
		JLabel lblNeiRateSA = new JLabel("Neighborhood Rate:");
		lblNeiRateSA.setBounds(23, 104+32+32, 140, 22);
		SimulatedPanel.add(lblNeiRateSA);
		
		NeiSAField = new JFormattedTextField(formatter);
		
		NeiSAField.setBounds(124+53, 104+32+32, 38+30, 20);
		NeiSAField.setEditable(false);
		SimulatedPanel.add(NeiSAField);
		NeiSAField.setColumns(10);
		
		/*
		 * add Neighborhood rate slider---------------------------
		 */
		
		JSlider NeiSlider_sa = new JSlider(0, 100, 1);
		NeiSlider_sa.setPaintTicks(true);
		NeiSlider_sa.setMajorTickSpacing(10);
		NeiSlider_sa.setMinorTickSpacing(5);
		
	        
		NeiSlider_sa.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				double v = (double) NeiSlider_sa.getValue()/100;
				dc.format(v);
				NeiSAField.setValue(v);
				
			}
			 
			 
		 });
		 
		 /*
		  * Set the slider position, activiate the labels and add it to the GUI
		*/		 
		NeiSlider_sa.setLabelTable(position);
		NeiSlider_sa.setPaintLabels(true);
		NeiSlider_sa.setBounds(23, 104+32+32+30, 200, 90);
		SimulatedPanel.add(NeiSlider_sa);
		
		MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("####.##");
            
        } catch (ParseException e) {
            e.printStackTrace();
        }

		
		JLabel lblDecFactSA = new JLabel("Control decreasing factor:");
		lblDecFactSA.setBounds(23, 310, 190, 22);
		SimulatedPanel.add(lblDecFactSA);
		
		
		ControlFSAField = new JFormattedTextField();
		
		ControlFSAField.setBounds(124+53, 310, 38+30, 20);
		
		SimulatedPanel.add(ControlFSAField);
		ControlFSAField.setColumns(10);
		
		JLabel lblctrlParamFactSA = new JLabel("Control parameter:");
		lblctrlParamFactSA.setBounds(23, 310+34, 190, 22);
		SimulatedPanel.add(lblctrlParamFactSA);
		
		ControlPSAField = new JFormattedTextField(formatter);
		
		ControlPSAField.setBounds(124+53, 310+34, 38+30, 20);
		
		SimulatedPanel.add(ControlPSAField);
		ControlPSAField.setColumns(10);
		
		
		XYSeries seriesSA = new XYSeries("Simulated Annealing");
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelMenu.setVisible(true);
				SimulatedPanel.setVisible(false);
			}
		});
		btnCancelar_1.setBounds(23, 353+200, 89, 23);
		SimulatedPanel.add(btnCancelar_1);
		
		
		Button ImportButton = new Button("Import");
		ImportButton.setBounds(296, 70, 70, 22);
		ImportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
			    int returnValue = fileChooser.showOpenDialog(null);
			    if (returnValue == JFileChooser.APPROVE_OPTION)
			    {
			        selectedFile = fileChooser.getSelectedFile();
			        pathDirectory = selectedFile.toString();
			        fileChooser.setCurrentDirectory(new File("C:/"));
			        Weights.setText(fileChooser.getSelectedFile().getAbsolutePath());
			    }
			    
			    
				
			
			}	
		});
		SimulatedPanel.add(ImportButton);
		
		JPanel TableSA = new JPanel();		
		tableSA = new JTable(tableDataSA, columnNamesSA);
		DefaultTableModel dtmSA = new DefaultTableModel(0, 0);
		tableSA.setModel(dtmSA);
		String headerSA[] = new String[] { "Iteration", "Fitness", "Structure"
	          };

		// add header in table model     
		dtmSA.setColumnIdentifiers(headerSA);
		
		JButton btnRun2 = new JButton("Run");
		btnRun2.setBounds(23, 275+240, 89, 23);
		btnRun2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Scanner sc2 = null;
				try{
					sc2 = new Scanner(new File(pathDirectory));
				} catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
				while(sc2.hasNextLine())
				{
					Scanner s2 =  new Scanner(sc2.nextLine());
					while(s2.hasNext())
					{
						
						String s = s2.next();
						
						
						
						counter++;
					}
					s2.close();
					
				}
				
				weights = new double[counter];
				counter = 0;
				try{
					sc2 = new Scanner(new File(pathDirectory));
				} catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
				while(sc2.hasNextLine())
				{
					Scanner s2 =  new Scanner(sc2.nextLine());
					while(s2.hasNext())
					{
						
						String s = s2.next();
						
						
						weights[counter] = Double.parseDouble(s);
						counter++;
					}
					s2.close();
					
				}
				
				
				
				sc2.close();
				counter = 0;
				
				if(btnRun2.isEnabled())  // AQUI VAMOS ATIVAR O BOTÃO RUN QUE IRÁ FAZER TRIGGER DA CLASSE EXPERIMENT TASK ONDE ESTARA IMPLEMENTADO O ALGORITMO
		        {
					/*System.out.println("pop: " + Integer.valueOf(PopSizeField.getText()));
					int popSize = Integer.valueOf(PopSizeField.getText());
		            int NumGenerations = Integer.valueOf(GenNumField.getText());
		            String MRate = MutationField.getText().replaceAll(",",".");
		            String CRate = CrossoverField.getText().replaceAll(",",".");
		            System.out.println("mutation rate:" + MRate);
		            System.out.println("crossover rate:" + CRate);
		            double crossoverRate = Double.valueOf(CRate);
		            double mutationRate = Double.valueOf(MRate);
		            int tournamentSize = Integer.valueOf(TournamentField.getText());
		            double capacity = Double.valueOf(CapacityField.getText());
		            boolean Maximization = false;
		            double inputs[] = weights;
		            boolean elitism = true;
		            int stringhLength = weights.length;
		           
	            	
		            GeneticAlgorithm R = new GeneticAlgorithm(this, stringhLength,capacity, Maximization ,elitism ,popSize, tournamentSize, NumGenerations, inputs, crossoverRate, mutationRate,700, 420);
		            R.Run();
		            */
					
					 Parameters_SA.capacity = Double.valueOf(CapacityFieldSA.getText());
					Parameters_SA.NumIterations = Integer.valueOf(NumIterSAField.getText());
					Parameters_SA.Slength = weights.length;
					
					Parameters_SA.neighbourrate = Double.valueOf(NeiSAField.getText().replaceAll(",", "."));
					Parameters_SA.controldecreasingFactor = Double.valueOf(ControlFSAField.getText());
					Parameters_SA.controlParamenter = Double.valueOf(ControlPSAField.getText());
					
					Parameters_SA.weights = weights;
					SimulatedAnnealing SA = new SimulatedAnnealing();
					SA.run();
		            
		            
		            dtm.setRowCount(0);
		            
		            for(int i = 0; i <Parameters_SA.NumIterations;i++)
		            {
		            	seriesSA.addOrUpdate(i, SA.fitnessesSA[i]);
		            	
		            	dtmSA.addRow(new Object[] { i, SA.fitnessesSA[i], SA.structureArray[i]});
		            	
		            }
		            
		        }
				
			}
				   
		});
		
		SimulatedPanel.add(btnRun2);
		SimulatedPanel.setVisible(false);
		
		JButton btnGeneticAlgortihms = new JButton("Genetic Algortihms");
		btnGeneticAlgortihms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GeneticPanel.setVisible(true);
				panelMenu.setVisible(false);
				
			}
		});
		btnGeneticAlgortihms.setBounds(10, 11, 156, 30);
		panelMenu.add(btnGeneticAlgortihms);
		
		JButton btnSimulated = new JButton("Simulated Annealing");
		btnSimulated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimulatedPanel.setVisible(true);
				panelMenu.setVisible(false);
			}
		});
		btnSimulated.setBounds(176, 11, 156, 30);
		panelMenu.add(btnSimulated);
		
		/* ***************** TABS SA **************/
		
		

		JTabbedPane tabbedPaneSA = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneSA.setBounds(374, 5, 700, 516);
		SimulatedPanel.add(tabbedPaneSA);
		
				
		

		
		
		JScrollPane tablePaneSA = new JScrollPane(tableSA);
		tabbedPaneSA.addTab("Table", null, tablePaneSA, null);
		
		
		
		/* ***************** GRAFICO SA **************/

		
		GraphPainter GPSA = new GraphPainter();
       /* for (int i = 0; i < 10; i++) 
        {
           // GP.AddGeneration(i,1, 120);
            GuiPainter PaintTask = new GuiPainter(this, i);
            PaintTask.start();
        }
*/
		
        
       
       

     // Add the series to your data set
        XYSeriesCollection datasetSA = new XYSeriesCollection();
        datasetSA.addSeries(seriesSA);

     // Generate the graph
        JFreeChart chartSA = ChartFactory.createXYLineChart(
        "Simulated Annealing Algorithm", // Title
        "Iterations", // x-axis Label
        "Best fitness", // y-axis Label
        datasetSA, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        	);
        
        JPanel graphPanelSA = new JPanel();
		tabbedPaneSA.addTab("Graph", null, graphPanelSA, null);
		
		ChartPanel CPSA = new ChartPanel(chartSA);

		graphPanelSA.add(CPSA,BorderLayout.CENTER);
		graphPanelSA.validate();
		
		
	}

	 
}
