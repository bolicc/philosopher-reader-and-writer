package exo2;

public class ReadWritePriority {
	int numberofReaders = 0;
	int numberofWriters = 0;
	int numberofRequestedWriters = 0;
	//int id;
	int signal[][]=new int [1000][2];
	int t = 0;
	public static int k=0;
	public static int j=0;
	public static int pointer=0;
	boolean round=false;

	public void getPointer() {
		pointer = (k - j);
	}

	public synchronized void startRead() {

		while (numberofWriters > 0 || numberofRequestedWriters > 0
				|| numberofReaders > 0) {
			try {
				wait();
				// Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		numberofReaders++;
	}

	public synchronized void endRead() {
		numberofReaders--;
		notifyAll();
		try {
			Thread.sleep((long) ((long) Math.random() * 700.0));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void startWrite() {

		numberofRequestedWriters++;
		while (numberofReaders > 0 || numberofWriters > 0) {
			try {

				wait();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		numberofRequestedWriters--;
		numberofWriters++;
	}

	public synchronized void endWrite() {

		numberofWriters--;
		notifyAll();
		try {
			Thread.sleep((long) ((long) Math.random() * 700.0));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
