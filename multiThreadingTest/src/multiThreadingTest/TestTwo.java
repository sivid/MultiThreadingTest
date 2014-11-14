package multiThreadingTest;

public class TestTwo {

	public static void main(String[] args) {
		RunnableDemoTwo r1 = new RunnableDemoTwo("Thread-1");
		r1.start();
		RunnableDemoTwo r2 = new RunnableDemoTwo("Thread-2");
		r2.start();
	}
}

class RunnableDemoTwo implements Runnable{
	private String threadName;
	private Thread t;
	
	RunnableDemoTwo (String name){
		threadName = name;
		System.out.println("Creating " + threadName);
	}
	
	public void run(){
		System.out.println("Running " + threadName + "is running");
		try{
			for (int i=0; i<5; i++){
				System.out.println("Thread " + threadName + ", " + i);
				Thread.sleep(40);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Thread " + threadName + " exiting");
	}
	
	public void start(){
		if (t == null){
			t = new Thread(this, threadName);
			t.start();
		}
	}
}