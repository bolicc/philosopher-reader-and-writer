package exo1;

public class dining  {

public static void main(String[] args){
	Fork f1=new Fork(1);
	Fork f2=new Fork(2);
	Fork f3=new Fork(3);
	Fork f4=new Fork(4);
	Fork f5=new Fork(5);
	Philosophers p1=new Philosophers(1,f5,f1);
	Philosophers p2=new Philosophers(2,f1,f2);
	Philosophers p3=new Philosophers(3,f2,f3);
	Philosophers p4=new Philosophers(4,f3,f4);
	Philosophers p5=new Philosophers(5,f4,f5);
	Thread t1=new Thread(p1);
	Thread t2=new Thread(p2);
	Thread t3=new Thread(p3);
	Thread t4=new Thread(p4);
	Thread t5=new Thread(p5);
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	t5.start();
}

}
