import java.util.*;

/**
 * Hierarchical clustering
 * The class defines the individual clusters, just as a BinaryTree of Samples
 * A static method performs the clustering
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 */

public class HierCluster extends BinaryTree<Sample> {
	public HierCluster(Sample centroid) {
		super(centroid);		
	}
	
	public HierCluster(Sample centroid, HierCluster left, HierCluster right) {
		super(centroid, left, right);
	}
	
	/**
	 * Clusters the data into a hierarchical cluster tree
	 */
	public static HierCluster cluster(ArrayList<Sample> data) {
		// YOUR CODE HERE
	}
}
