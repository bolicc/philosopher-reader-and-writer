package exo1;

public class Philosophers implements Runnable{
	private Fork left;
	private Fork right;
	private int id;

	public Philosophers(int id, Fork f1, Fork f2){
		left=f1;
		right=f2;
		this.id=id;
	}
	
	public void eat(){
		/*
		 //without semaphore
		if(left.availabe && right.availabe) {
			left.got();
			right.got();
			System.out.println(id+" is eating");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			right.put();
			left.put();
		}
		*/
		if(left.got()){
			if(right.got()){
				System.out.println(id+" is eating");
				left.put();
				right.put();
			}
			else{
				left.put();
				eat();
			}}
		else {
				eat();		
		}
	}
	
	public void run(){
		
		while(true) {
			
			think();
			
			eat();
			
			}
		
		
	}
public void think(){
	
	System.out.println(id+" is thinking");
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
