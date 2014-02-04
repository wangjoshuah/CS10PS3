import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * GUI for testing clustering code
 * 
 * @author Chris Bailey-Kellogg Dartmouth CS 10, Fall 2012; revised Winter 2014
 */
public class ClusterGUI extends DrawingFrame {
	// The input data
	private ArrayList<Sample> samples;							// the samples themselves
	private int numFeatures, numSamples;						// how many
	private double minValue, maxValue;							// their min and max values, overall
	private int cellWidth, cellHeight;							// how big to plot the cells for the values
	
	// Clustering
	private HierCluster tree;									// the hierarchical clustering tree	
	private ArrayList<KCluster> kclusters;						// the k-means clusters
	private int k;												// how many clusters
	private boolean converged = false;							// has k-means converged?
	
	public ClusterGUI(String filename, int width, int height, int numClusters) {
		super("Clustering");
		k = numClusters;
		
		// Load the data
		samples = Sample.load(filename);
		numFeatures = samples.get(0).values.length;  // assumes uniform
		numSamples = samples.size();
		cellWidth = width / numFeatures;
		cellHeight = height / numSamples;
		System.out.println("cells are "+cellWidth+"*"+cellHeight);
		if (cellWidth == 0 || cellHeight == 0) {
			System.err.println("window isn't big enough -- adjust width and/or height");
			System.exit(1);
		}
		
		// Find min and max values in the data (for color scale)
		minValue = samples.get(0).values[0];
		maxValue = minValue;
		for (Sample s : samples) {
			for (double v : s.values) {
				if (v<minValue) minValue = v;
				else if (v>maxValue) maxValue = v;
			}
		}	

		// Complete the DrawingFrame initialization
		finishGUI(cellWidth*numFeatures, cellHeight*numSamples);

		// Mouse press steps from initial data to hierarchical to subsequent iterations of k-means
		System.out.println("showing initial data; click for hierarchical");
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (tree == null) {
					// Do hierarchical clustering
					tree = HierCluster.cluster(samples);
					System.out.println("hierarchical clustering:");
					System.out.println(tree);
					System.out.println("click for k-means");
					repaint();
				}
				else if (kclusters == null || converged) {
					// Start (or re-start) k-means clustering
					converged = false;
					kclusters = KCluster.init(samples, k);
					System.out.println("k-means initialization");
					System.out.println(kclusters);
					System.out.println("click for iteration");
					repaint();
				}
				else {
					// Another k-means iteration
					ArrayList<KCluster> newclusters = KCluster.recluster(samples, kclusters);
					converged = KCluster.checkConverged(kclusters, newclusters);
					kclusters = newclusters;
					System.out.println(kclusters);
					if (converged) System.out.println("converged; click to reinitialize");
					else System.out.println("click for another iteration");
					repaint();
				}
			}
		});
    }
	
	/**
	 * Draws the list of samples on g, starting at the given sample row
	 * Each value is a cell colored green (min) to black (midpoint) to red (max)
	 */
	public void drawSamples(Graphics g, ArrayList<Sample> samples, int row) {
		for (int i=0; i<samples.size(); i++) {
			double[] values = samples.get(i).values;
			for (int j=0; j<numFeatures; j++) {
				double v = -1 + 2 * (values[j] - minValue) / (maxValue - minValue); // (-1,1) range
				// -1 to 0 -> bright green to black
				// 0 to 1 -> black to bright red
				if (v < 0) g.setColor(new Color((int)((-v)*255), 0, 0));
				else g.setColor(new Color(0, (int)(v*255), 0));
				g.fillRect(j*cellWidth, (i+row)*cellHeight, cellWidth, cellHeight);
			}
		}
	}

	/**
	 * Overrides in order to show array data
	 */
	public void draw(Graphics g) {
		if (kclusters != null) {
			// Show clusters, separated by lines
			int row = 0;
			for (KCluster cluster : kclusters) {
				drawSamples(g, cluster.members, row);
				if (row > 0) {
					g.setColor(Color.blue);
					g.drawLine(0,row*cellHeight, getWidth(),row*cellHeight);
				}
				row += cluster.members.size();
			}
		}
		else if (tree != null) {
			// Just reorder to follow tree structure
			drawSamples(g, tree.fringe(), 0);
		}
		else {
			// Show input data
			drawSamples(g, samples, 0);
		}
	}
	
	public static void main(String[] args) {
		// Start up the GUI; initially showing the samples
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Small test case
				new ClusterGUI("inputs/simple.csv", 400, 200, 3);
				
				// Larger test case
//				new ClusterGUI("inputs/cancer.csv", 1000, 600, 2);
			}
		});
	}
}
