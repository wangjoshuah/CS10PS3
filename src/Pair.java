/**
 * Utility class for hierarchical clustering,
 * storing a pair of clusters that could be merged to a new cluster,
 * and using the distance between them to implement the Comparable interface
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 */

public class Pair implements Comparable<Pair> {
	public HierCluster left, right;	// two trees that could be merged
	public double dist;							// distance between their centroids

	public Pair(HierCluster left, HierCluster right) {
		this.left = left; this.right = right;
		this.dist = left.data.distance(right.data);
	}

	public int compareTo(Pair o) {
		// which distance is smaller?
		return (int)Math.signum(dist - o.dist);
	}
	
	/**
	 * Computes the centroid of all the samples in the two trees, by a weighted average of the two centroids
	 */
	public Sample centroid() {
		double v[] = new double[left.data.values.length];
		// YOUR CODE HERE
		return new Sample(v);
	}
	
	public String toString() {
		return left + "+\n" + right + "@" + dist;
	}
}