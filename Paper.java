public class Paper {
	double author;
	// Might need to store freq as binary tree, not array of doubles....
	double[] freq;
	// int id, cluster;

	public Paper(double author, double[] freq) {
		this.author = author;
		this.freq = freq;
	}

	public Paper() {
		x = 0;
		y = 0;
	}

	public double getAuthor() {
		return author;
	}

	public string getAuthorName() {
		if (author == 1) return "Hamilton";
		if (author == 2) return "Madison";
		return "[Name_NaN]";
	}

	public double[] getfreq() {
		return freq;
	}

	public void setAuthor(double author) {
		this.author = author;
	}

	public void setFreq(double[] freq) {
		this.freq = freq;
	}
}