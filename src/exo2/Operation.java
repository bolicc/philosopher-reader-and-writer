package exo2;

public class Operation {
	public static void main(String[] args) {
		Memory mem = new Memory(1000);
		// System.out.println(mem.getLength());
		ReadWritePriority r = new ReadWritePriority();

		Writer wr1 = new Writer(r, mem, 1);
		Writer wr2 = new Writer(r, mem, 2);
		Writer wr3 = new Writer(r, mem, 3);
		Reader re1 = new Reader(r, mem, 1);
		Reader re2 = new Reader(r, mem, 2);
		Reader re3 = new Reader(r, mem, 3);
		Thread t1 = new Thread(re1);
		Thread t2 = new Thread(re2);
		Thread t3 = new Thread(re3);
		Thread t4 = new Thread(wr1);
		Thread t5 = new Thread(wr2);
		Thread t6 = new Thread(wr3);

		t4.start();
		t5.start();
		t6.start();
		t1.start();
		t2.start();
		t3.start();

	}
}
