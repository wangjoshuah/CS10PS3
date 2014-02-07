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
		if(members.isEmpty()) {
			members = null;
		}
		else {
			for(int j = 0; j < members.get(0).values.length; j++) { //for every double in a given sample
				System.out.println(members.get(0).values.length);
				prototype = members.get(0);
				double mean = 0;
					for(int i = 0; i < members.size(); i++) { //for every Sample in members	
					mean += members.get(i).values[j]; //add the jth double in each sample together
				}
				mean = (int) mean/members.size(); //divide the sum of the jth double in each sample by the number of samples
				prototype.values[j] = mean; //set the mean as the jth value of the prototype
				//prototype is now equal to the centroid
			}
		}
	}
	
	/**
	 * Initializes a set of k clusters for the given samples,
	 * by assigning each sample to a random cluster, and computing centroids
	 */
	public static ArrayList<KCluster> init(ArrayList<Sample> samples, int k) {
		ArrayList<KCluster> directory = new ArrayList<KCluster>();
		

		//Create Kcluster of size k
		for(int i = 0; i < k; i++) {
			//create a new cluster
			KCluster kc = new KCluster();
			directory.add(kc);
		}
		
		//for each sample, assign it to a randomly selected cluster
		for(int i = 0; i < samples.size(); i++ ) {
			double randomN = Math.random()*k;
			directory.get((int)randomN).add(samples.get(i));
		}
		
		//for each Kcluster, get the centroid
		for(int i = 0; i < k; i++) {
			directory.get(i).setPrototype();
		}
		
		
		
		return directory;
	}
	
	/**
	 * Produces a new set of clusters by re-dividing the samples according to the centroids in the old clusters,
	 * and computing centroids
	 */
	public static ArrayList<KCluster> recluster(ArrayList<Sample> samples, ArrayList<KCluster> oldClusters) {
		// YOUR CODE HERE
		ArrayList<KCluster> newDirectory = new ArrayList<KCluster>();
		
		int ClosestIndex = 0;
		
		//Create Kcluster of size k
		for(int i = 0; i < oldClusters.size(); i++) {
			//create a new cluster
			KCluster kc = new KCluster();
			newDirectory.add(kc);
		}
		
		for(int i = 0; i < samples.size(); i++) { //for each sample in samples
			ClosestIndex = closestClusterIndex(samples.get(i), oldClusters); //get the index of the cluster with the closest centroid
			KCluster temp = newDirectory.get(ClosestIndex);
			temp.add(samples.get(i)); //add a given sample to that cluster of newDirectory

		}
		
		for(int i = 0; i < oldClusters.size(); i++) { //for each cluster in newDirectory
			newDirectory.get(i).setPrototype(); //get the centroid for that cluster
		}
		
		return newDirectory;
	}
	
	/**
	 * Determines which of the clusters has the centroid closets to the sample,
	 * and returns the cluster's index
	 */
	public static int closestClusterIndex(Sample s, ArrayList<KCluster> directory) {
		// YOUR CODE HERE
		double closestDistance = s.distance(directory.get(0).getPrototype()); //initialize ClosestDistance by setting it to the distance of the centroid in the first cluster of the directory
		int index = 1;
		for(int i = 0; i < directory.size(); i++) { //for each kcluster in the directory
			System.out.println(closestDistance + "a");
			System.out.println(s.distance(directory.get(1).getPrototype()) + "b");
			System.out.println(s.distance(directory.get(2).getPrototype()) + "c");
			if( closestDistance > s.distance(directory.get(i).getPrototype()) ) { //check if the prototype in Cluster number i has the shortest distance to s
				index = i; //if so, index becomes the index of that cluster
				closestDistance = s.distance(directory.get(i).getPrototype()); //keep the new closest distnace

			}
		}
		return index;
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
	

	public void add(Sample element) {
		members.add(element);
	}
	public Sample getPrototype() {
		return prototype;
	}
}
