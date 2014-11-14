package multiThreadingTest;

public class CountNumbersTwo {
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

class RunnableCounterDemoTwo implements Runnable{
	private Thread t;
	private String threadName;
	RunnableCounterDemoTwo(String name){
		threadName = name;
		System.out.println("Creating " + threadName);
	}
	public void run(){
		int myCount = 0;
		SingletonDemoTwo s = SingletonDemoTwo.getInstance();
		try{
			while (myCount <= 12){
				synchronized(s.getLock()){
					myCount = s.getCountAndIncrement();
					if (myCount <= 12){
						System.out.println("I'm " + threadName + ", the counter is now " + myCount);
					}		
				}
				Thread.sleep(50);
			}
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	public void start(){
		if (t == null){
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

class SingletonDemoTwo{
	private int count = 0;
	private Object lock = new Object();
	private SingletonDemoTwo(){}
	private static class SingletonHelper{
		private static final SingletonDemoTwo INSTANCE = new SingletonDemoTwo();
	}
	public static SingletonDemoTwo getInstance(){
		return SingletonHelper.INSTANCE;
	}
	public int getCountAndIncrement(){
		count = count + 1;
		return count;
	}
	public Object getLock(){
		return lock;
	}
}
