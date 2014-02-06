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
		//create an ArrayList to store HeirCluster fringe leaves
		ArrayList<HierCluster> sampleTree = new ArrayList<HierCluster>(); 
		//instantiate all leaves from the sample data
		for (int i = 0; i < data.size(); i++) {
			HierCluster leaf = new HierCluster(data.get(i));
			//then add all leaves to the AL for leaves
			sampleTree.add(leaf);
		}
		
		//create a priority queue to take pairs and check for lowest distance
		PriorityQueue<Pair> allPairs = new PriorityQueue<Pair>();
		//loop over AL to instantiate all possible pairs
		for (int i = 0; i < sampleTree.size(); i++) { //use the handshake algorithm
			for (int j = i + 1; j < sampleTree.size(); j ++) { //also known as round robin
				Pair handshake = new Pair(sampleTree.get(i), sampleTree.get(j)); //make a pair
				allPairs.add(handshake); //put it in the priority queue
			}
		}
		
		//use lowest pair to make branches
		while(sampleTree.size() > 1) { //while we still have 
			Pair closestPair = allPairs.remove(); //pop the closest pair
			if (sampleTree.contains(closestPair.left)) { //if the left leaf is still in the AL
				if (sampleTree.contains(closestPair.right)) { //and the right leaf is still there
					//for each loop each loop make a new pair with whatever's left in the AL
					sampleTree.remove(closestPair.left); //remove the left
					sampleTree.remove(closestPair.right); //remove the right
					//calculate the centroid for the pair,
					//use the pair as a new HierCluster with left and right as the Pair's left and right
					HierCluster branch = new HierCluster(closestPair.centroid(), closestPair.left, closestPair.right);
					for (int i = 0; i < sampleTree.size(); i++) { //for all remaining HierClusters,
						Pair handshake = new Pair(branch, sampleTree.get(i)); //make a pair with it
						allPairs.add(handshake); //and add it to the priority queue
					}
					sampleTree.add(branch); //add the HierCluster back into the array
				}
			}
		}
		//remove the last thing in the tree from it
		HierCluster tree = sampleTree.remove(0);
		//and return it
		return tree;		
	}
}
