import java.util.*;

/**
 * Hierarchical clustering The class defines the individual clusters, just as a
 * BinaryTree of Samples A static method performs the clustering
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
		//keep priorityQueue<Pair> 
		//keep array list of samples (data)
		//keep ArrayList<HeirCluster>
		//heircluster AL keeps list of things we need to put into our tree
		//PriorityQueue does handshake algorithm and then pulls out lowest distance
		//make pair of it and put it in the AL
		
		// YOUR CODE HERE
		
		ArrayList<HierCluster> sampleLeaves = new ArrayList<HierCluster>();
		for (int i = 0; i < data.size(); i++) {
			HierCluster leaf = new HierCluster(data.get(i));
		}
		
		PriorityQueue<Pair> allPairs = new PriorityQueue<Pair>();
		
		

	}
}
