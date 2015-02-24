public class Paper {
	Object author;
	// Might need to store freq as binary tree, not array of doubles....
	double[] freq;
	// int id, cluster;

	public Paper(double author, double[] freq) {
		if (author == 1) this.author = "Hamilton";
		else if (author == 2) this.author = "Madison";
		else this.author = "[Name_Nan]";
		this.freq = freq;
	}

	public Paper() {
		x = 0;
		y = 0;
	}

	public Object getAuthor() {
		return author;
	}

	public double[] getFreq() {
		return freq;
	}

	public void setAuthor(double author) {
		if (author == 1) this.author = "Hamilton";
		else if (author == 2) this.author = "Madison";
		else this.author = "[Name_Nan]";
	}

	public void setFreq(double[] freq) {
		this.freq = freq;
	}

	public String toString() {
		return author + " " + freq.length;
	}
}