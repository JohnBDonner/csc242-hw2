import java.io.*;
import java.util.*;

public class Problem6 {
	private int k;
	private ArrayList<Object> classes;
	private ArrayList<Paper> dataSet;

	public static void main(String[] args) {
		ArrayList<Paper> train, tune, test, samples;

		train = read("train_86_by_71.txt");
		tune = read("tune_20_by_71.txt");
		test = readTest("test_12_by_70.txt");

		for (Paper p : train) {
			System.out.println(p.getFreq()[0]);
		}

		Problem6 nn = new Problem6(train, 3);

		// This print statement is for the test data
		//System.out.println("Classified as: "+nn.classify(new Paper(new double[]{170, 60},"Ignore")));
		System.out.println("******** TUNING ********");
		double r = 0.0;
		double w = 0.0;
		for (Paper p : tune) {
			if (nn.classify(p).equals(p.getAuthor())) {
				System.out.println("Matched!");
				r++;
			}
			else {
				System.out.println("WRONG");
				w++;
			}
			System.out.println("Classified as: "+nn.classify(p)+" compared to: "+p.getAuthor());
		}
		System.out.println("Error = " + w/(r+w));
		
		System.out.println("\n\n******** TESTING ********\n\n");
		for (Paper p : test) {
			System.out.println("Classified as: "+nn.classify(p));
		}
	}

	public Problem6(ArrayList<Paper> dataSet, int k){
		this.classes = new ArrayList<Object>();
		this.k = k;
		this.dataSet = dataSet;
		
		//Load different classes
		for(Paper p : dataSet){
			if(!classes.contains(p.getAuthor())) classes.add(p.getAuthor());
		}

	}

	private Paper[] getNearestNeighbourType(Paper x){
		Paper[] retur = new Paper[this.k];
		double fjernest = Double.MIN_VALUE;
		int index = 0;
		for(Paper tse : this.dataSet){
			double distance = distance(x,tse);
			if(retur[retur.length-1] == null){ //Hvis ikke fyldt
				int j = 0;
				while(j < retur.length){
					if(retur[j] == null){
						retur[j] = tse; break;
					}
					j++;
				}
				if(distance > fjernest){
					index = j;
					fjernest = distance;
				}
			}
			else{
				if(distance < fjernest){
					retur[index] = tse;
					double f = 0.0;
					int ind = 0;
					for(int j = 0; j < retur.length; j++){
						double dt = distance(retur[j],x);
						if(dt > f){
							f = dt;
							ind = j;
						}
					}
					fjernest = f;
					index = ind;
				}
			}
		}
		return retur;
	}

	private static double convertDistance(double d){
		return 1.0/d;
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

	public Object classify(Paper e){
		HashMap<Object,Double> classcount = new HashMap<Object,Double>();
		Paper[] de = this.getNearestNeighbourType(e);
		for(int i = 0; i < de.length; i++){
			double distance = Problem6.convertDistance(Problem6.distance(de[i], e));
			if(!classcount.containsKey(de[i].getAuthor())){
				classcount.put(de[i].getAuthor(), distance);
			}
			else{
				classcount.put(de[i].getAuthor(), classcount.get(de[i].getAuthor())+distance);
			}
		}
		//Find right choice
		Object o = null;
		double max = 0;
		for(Object ob : classcount.keySet()){
			if(classcount.get(ob) > max){
				max = classcount.get(ob);
				o = ob;
			}
		}
		
		return o;
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

	public static ArrayList<Paper> readTest(String file) {
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
	            	double[] freq = new double[stringSeparated.length];
	            	for (int i = 0; i < stringSeparated.length; i++) {
	            		// Need to separate the author from the data.
	            		// Perhaps use an object to distinguish between the two.
	            		// dataset.insert(stringSeparated[i]);
	            		freq[i] = Double.parseDouble(stringSeparated[i]);
	            	}
	            	Paper paper = new Paper(0, freq);
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