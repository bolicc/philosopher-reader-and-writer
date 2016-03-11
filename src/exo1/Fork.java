  package exo1;
import java.util.concurrent.Semaphore;

public class Fork {
private Semaphore sem=new Semaphore(1);
public boolean availabe;
private int id;
public Fork(int id){
	this.id=id;
	availabe=true;
}

public void setStatus(boolean status){
	this.availabe=status;
}
/*public synchronized void got(){
	//without semaphore
	 * while(!availabe){
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.id=id;
	}
	availabe=false;
	*/
public boolean got(){
	//with semaphore
	
	return sem.tryAcquire();
	
	}

/*public synchronized void put(){
	//without semaphore
	  availabe=true;
	notifyAll();
	
	sem.release();
	availabe=true;
}*/
public void put(){
	
	sem.release();
	availabe=true;
}
}

