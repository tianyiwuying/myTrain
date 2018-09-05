package myTrain;

public class ThreadClass {
	
	public static void main(String[] args) {
		// Java线程类也是一个object类,它的实例都继承自java.lang.Thread或其子类。 可以用如下方式用java中创建一个线程：
		//一旦线程启动后start方法就会立即返回，而不会等待到run方法执行完毕才返回。就好像run方法是在另外一个cpu上执行一样
		//。当run方法执行后，将会打印出字符串MyThread running。
		//1
		MyThread objThread=new MyThread();
		objThread.start();
		
		//2
		//当新的线程的run方法执行以后，计算机将会打印出字符串”Thread Running”。
		Thread thread=new Thread(){
			public void run(){
				System.out.println("ok");
			}
		};
		thread.start();
		
		//当线程运行时，它将会调用实现了Runnable接口的run方法
		//3
		Thread threadThree=new Thread(new MyRunnable());
		threadThree.start();
		
		//因为线程池可以有效的管理实现了Runnable接口的线程，如果线程池满了，新的线程就会排队等候执行，直到线程池空闲出来为止
		//4
		Runnable objRunable=new Runnable(){
			public void run(){
				System.out.println("ok");
			}
		};
		Thread threadFour = new Thread(objRunable);
		thread.start();
	}
	//第一种方式
	public static class MyThread extends Thread{
		public void run(){
			System.out.println("runing");
		}
	}
	//第二种方式
	public static class MyRunnable implements Runnable{
		public void run(){
			 System.out.println("MyRunnable running");
		}
	}
}
