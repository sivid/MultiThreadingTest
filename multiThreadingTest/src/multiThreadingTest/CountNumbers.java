package multiThreadingTest;

public class CountNumbers {

	public static void main(String[] args) {
		RunnableCounterDemoTwo r1 = new RunnableCounterDemoTwo("Thread-A");
		RunnableCounterDemoTwo r2 = new RunnableCounterDemoTwo("Thread-B");
		RunnableCounterDemoTwo r3 = new RunnableCounterDemoTwo("Thread-C");
		RunnableCounterDemoTwo r4 = new RunnableCounterDemoTwo("Thread-D");
		r1.start();
		r2.start();
		r3.start();
		r4.start();
	}
}

class RunnableCounterDemo implements Runnable{
	private Thread t;
	private String threadName;
	RunnableCounterDemo(String name){
		threadName = name;
		System.out.println("Creating " + threadName);
	}
	public void run(){
		// read counter from singleton, add 1, print
		int myCount = 0;
		SingletonDemoTwo s = SingletonDemoTwo.getInstance();
		try{
			while (myCount <= 12){
				myCount = s.getCountAndIncrement();
				if (myCount <= 12){
					System.out.println("I'm " + threadName + ", the counter is now " + myCount);
				}
				Thread.sleep(50);
			}
		} catch (InterruptedException e){
			// nada
		}
	}
	public void start(){
		if (t == null){
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

class SingletonDemo{
	private int count = 0;
	private SingletonDemo(){}
	private static class SingletonHelper{
		private static final SingletonDemo INSTANCE = new SingletonDemo();
	}
	public static SingletonDemo getInstance(){
		return SingletonHelper.INSTANCE;
	}
	public synchronized int getCountAndIncrement(){
		count = count +1;
		return count;
	}
}
