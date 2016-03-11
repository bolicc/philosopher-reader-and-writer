package exo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Reader implements Runnable {
	public ReadWritePriority reader;
	int id;
	File file;
	volatile int memorysize;
	int temp;
	int i;
	char c;
	byte[] b = new byte[(int) (Math.random() * (100 - 1)) + 1];
	Memory mem;
	int dd;
	FileOutputStream fo;

	public Reader(ReadWritePriority reader, Memory m, int id) {
		this.reader = reader;
		mem = m;
		this.id = id;

	}

	public Reader(ReadWritePriority reader, Memory m) {
		this.reader = reader;
		mem = m;

	}

	// write data out to file
	public void Rwrite() {
		if (id == reader.signal[reader.j][1]) {
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				fo.write(b);

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("reader " + id + " finishes reading");
		}
	}

	public void Rread() {

		if (((mem.mem.length - reader.j) > b.length)
				&& (id == reader.signal[reader.j][1])) {

			System.out.println("reader " + id + " starts to read from writer"
					+ reader.signal[reader.j][1]);
			for (int i = 0; i < b.length; i++) {
				b[i] = mem.mem[reader.j];
				reader.j++;
				reader.getPointer();

			}

		} else if (((mem.mem.length - reader.j) < b.length)
				&& (id == reader.signal[reader.j][1])) {

			System.out.println("reader " + id + " starts to read from writer"
					+ reader.signal[reader.j][1]);
			for (i = 0; i < mem.mem.length - reader.j; i++) {
				b[i] = mem.mem[reader.j];
				reader.j++;
				reader.getPointer();
			}
			temp = i;
			for (int s = 0; s < b.length - (mem.mem.length - reader.j); s++) {
				reader.j = 0;
				b[temp + s] = mem.mem[reader.j];
				reader.j++;
				reader.getPointer();
			}
		}

	}


	public void run() {

		file = new File("src/exo2/outfile.txt");
		try {
			fo = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {

			reader.startRead();
			if (reader.round) {

				Rread();

				Rwrite();
			} else {
				if (reader.pointer > 0) {
					Rread();
					Rwrite();
				}
			}

			reader.endRead();
		}
	}
}
