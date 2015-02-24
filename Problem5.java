import java.io.*;
import java.util.*;

public class Problem5 {
	private int k;
	private ArrayList<Object> classes;
	private ArrayList<DataEntry> dataSet;

	public static void main(String[] args) {
		ArrayList<Paper> train, tune, test, samples;

		train = read("train_86_by_71.txt");
		for (Paper p : train) {
			System.out.println(p.getFreq()[0]);
		}
		//tune = read("tune_20_by_71.txt");
		
	}

	public static double distance(Paper a, Paper b) {
		double distance = 0.0;
		int length = a.getFreq().length; // getX() returns a double[] of the dataEntry object
		for (int i = 0; i < length; i++) {
			double t = a.getFreq()[i] - b.getFreq()[i];
			distance = distance + t*t;
		}
		return Math.sqrt(distance);
	}

	public static ArrayList<Paper> read(String file) {
		ArrayList<Paper> dataset = new ArrayList<Paper>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	        StringBuilder sb = new StringBuilder();
	        String line = "";// = br.readLine();

	        int n = 0;
	        // iterate through the data and store it in a linked list
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	            if (line != null) {
	            	String[] stringSeparated = line.split("\\t");
	            	double author = Double.parseDouble(stringSeparated[0]);
	            	double[] freq = new double[stringSeparated.length-1];
	            	for (int i = 0; i < stringSeparated.length; i++) {
	            		// Need to separate the author from the data.
	            		// Perhaps use an object to distinguish between the two.
	            		// dataset.insert(stringSeparated[i]);
	            		if (i != 0) freq[i-1] = Double.parseDouble(stringSeparated[i]);
	            	}
	            	Paper paper = new Paper(author, freq);
	            	dataset.add(paper);
	            	n++;
	            	// System.out.println("line " + n + ": " + point.toString());
	            }
	        }
			//String everything = sb.toString();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    }
	    return dataset;
	}
}