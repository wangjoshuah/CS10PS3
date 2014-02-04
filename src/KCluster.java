import java.util.ArrayList;

/**
 * K-means clustering
 * The class defines the individual clusters, each with a centroid and list of members
 * Static methods perform k-means clustering in terms of lists of KClusters
 * 
 * @author Chris Bailey-Kellogg and Haris Baig, Dartmouth CS 10, Fall 2012
 */
public class KCluster {
	public Sample prototype;							// the middle of the cluster
	public ArrayList<Sample> members;			// all the samples of the cluster

	public KCluster() {
		members = new ArrayList<Sample>();
	}

	public String toString() {
		return members.toString();
	}
	
	/**
	 * Updates the prototype of the cluster based on the members
	 * If no members, sets it to null (be careful using it, then!)
	 */
	public void setPrototype() {
		// YOUR CODE HERE
	}
	
	/**
	 * Initializes a set of k clusters for the given samples,
	 * by assigning each sample to a random cluster, and computing centroids
	 */
	public static ArrayList<KCluster> init(ArrayList<Sample> samples, int k) {
		// YOUR CODE HERE
	}
	
	/**
	 * Produces a new set of clusters by re-dividing the samples according to the centroids in the old clusters,
	 * and computing centroids
	 */
	public static ArrayList<KCluster> recluster(ArrayList<Sample> samples, ArrayList<KCluster> oldClusters) {
		// YOUR CODE HERE
	}
	
	/**
	 * Determines which of the clusters has the centroid closets to the sample,
	 * and returns the cluster's index
	 */
	public static int closestClusterIndex(Sample s, ArrayList<KCluster> clusters) {
		// YOUR CODE HERE
	}
	
	/**
	 * Determines if the two cluster lists are the same, in that corresponding ones have the same members
	 * Only works if members are assigned to clusters in the same order.
	 */
	public static boolean checkConverged(ArrayList<KCluster> c1, ArrayList<KCluster> c2) {
		for (int i=0; i<c1.size(); i++)
			if (!c1.get(i).members.equals(c2.get(i).members)) return false;
		return true;
	}
}
