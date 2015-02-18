

public class Problem5 {
	public static void main(String[] args) {

		set_train = read("train_86_by_71.txt");
		set_tune = read("tune_20_by_71.txt");
		
	}

	public static LinkedList read(String file) {
		LinkedList dataset = new LinkedList();
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
	            	String[] stringSeparated = line.split("\\s+");
	            	Point point = new Point(Double.parseDouble(stringSeparated[0]), Double.parseDouble(stringSeparated[1]));
	            	dataset.insert(point);
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