import java.io.*;
import java.util.*;

/**
 * Sample data for clustering, with a name and an array of values
 * 
 * @author Chris Bailey-Kellogg and Haris Baig, Dartmouth CS 10, Fall 2012
 */
public class Sample {
	public String name;				// for printing
	public double[] values;		// the features
	
	public Sample(double[] values) {
		this.name = "*";
		this.values = values;
	}
	
	public Sample(String name, double[] values) {
		this.name = name; this.values = values;
	}
	
	/**
	 * Euclidean distance to the other sample
	 */
	public double distance(Sample other) {
		// YOUR CODE HERE
		//square the difference between each value and add that to the sum
		// we dont' need to sqrt it at the end because we're comparing the sqrtd values
		// sqrting won't change greater than or less than relations
		double sum = 0; //create our local variable we will return
		for (int i = 0; i < values.length; i++) { //loop through all values for each sample
			sum += Math.pow((this.values[i] - other.values[i]),2); //square the difference and add it to the sum 
		}
		return sum; //return our running total
		
	}
	
	public String toString() {
		return name;
	}

	/**
	 * Makes a printable string of the values; maybe useful for debugging
	 */
	public String valuesString() {
		String s = "[";
		for (int i=0; i<values.length; i++) {
			if (i>0) s += ",";
			s += values[i];
		}
		return s + "]";		
	}
	
	/**
	 * Parses a sample from a CSV line
	 */
	public static Sample parseSample(String line) {
		String[] strings = line.split(",");
		double[] values = new double[strings.length-1];
		for (int i=0; i<values.length; i++) 
			values[i] = Double.parseDouble(strings[i+1]);
		return new Sample(strings[0], values);
	}
	
	/**
	 * Loads a list of samples from a CSV (comma-separated values) file
	 */
	public static ArrayList<Sample> load(String filename) {
		ArrayList<Sample> samples = new ArrayList<Sample>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null)
				samples.add(parseSample(line));
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return samples;
	}
}
