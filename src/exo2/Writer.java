package exo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer implements Runnable {
	public ReadWritePriority writer;
	public byte[] b = new byte[(int)(Math.random()*(100-1))+1];
	Memory mem;
	int temp=0;
	int i;
	int num = 0;
	int id;
	FileInputStream fi;

	public Writer(ReadWritePriority writer, Memory m, int id) {
		this.writer = writer;
		mem = m;
		this.id = id;
	}

	public Writer(ReadWritePriority writer, Memory m) {
		this.writer = writer;
		mem = m;

	}

	// read data from file, store it in array b
	public void Wread() {

		try {
			fi.read(b);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("writer " + id + " finish writing");
	}

	public void Wwrite() {

		if ((writer.pointer < 1000) && ((mem.mem.length - writer.k) > b.length)) {

			for (int i = 0; i < b.length; i++) {

				mem.mem[writer.k] = b[i];
				writer.signal[writer.k][0] = mem.mem[writer.k];
				writer.signal[writer.k][1] = id;
				writer.k++;
				writer.getPointer();
			}

		} else if ((writer.pointer < 1000)
				&& ((mem.mem.length - writer.k) < b.length)) {

			for (int i = 0; i < mem.mem.length - writer.k; i++) {
				mem.mem[writer.k] = b[i];
				writer.signal[writer.k][0] = mem.mem[writer.k];
				writer.signal[writer.k][1] = id;
				writer.k++;
				writer.getPointer();
			}
			temp = i;
			for (int s = 0; s < b.length - (mem.mem.length - writer.k); s++) {
				writer.k = 0;
				mem.mem[writer.k] = b[temp + s];
				writer.signal[writer.k][0] = mem.mem[writer.k];
				writer.signal[writer.k][1] = id;
				writer.k++;
				writer.getPointer();

			}
		}

		System.out.println("writer " + id + " starts to write");
	}

	public void run() {

		try {
			fi = new FileInputStream(new File("src/exo2/infile.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {

			if ((writer.pointer < 1000)) {
				writer.startWrite();

				Wwrite();
				Wread();
				writer.endWrite();
			}
		}
	}
}
