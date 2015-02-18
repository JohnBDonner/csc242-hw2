

public class Hash {
	String[] hashtable;
	int size, nitems;
	
	public Hash(int size) {
		this.size = size;
		hashtable = new String[this.size];
		nitems = 0;
	}
	
	public void insert(String s) {
		int index = hash(s, size);
		index = check(index, s);
		hashtable[index] = s;
	}
	
	public boolean lookup(String s) {
		int index = hash(s, size);
		index = check(index, s);
		if (hashtable[index] != null) {
			if (hashtable[index].equals(s))
				return true;
		}
		return false;
	}
	
	public static int hash(String key, int tableSize) {
		int hashVal = 0;
		
		for (int i = 0; i < key.length(); i++) {
			hashVal = 37 * hashVal + key.charAt(i);
		}
		
		hashVal %= tableSize;
		if (hashVal < 0)
			hashVal += tableSize;
		
		return hashVal;
	}
	
	public int check(int orig, String s) {
		if (hashtable[orig] == null) {
			return orig;
		} else if (hashtable[orig].equals(s)) {
			return orig;
		} else if (orig < hashtable.length-1) {
			int newVal = orig + 1;
			return check(newVal, s);
		} else {
			int newVal = 0;
			return check(newVal, s);
		}
	}
}
