package exo2;

public class Memory {
	public static int l;
	boolean full = false;
	boolean empty = true;
	volatile byte mem[];

	Memory(int s) {
		l = s;
		mem = new byte[s];
		// System.out.println(Mem.length);

	}

	public int getLength() {

		return mem.length;
	}

	public boolean isEmpty() {
		return (mem == null);
	}

}
