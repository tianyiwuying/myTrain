package myTrain;

public class ThreadClass {
	
	public static void main(String[] args) {
		// Java�߳���Ҳ��һ��object��,����ʵ�����̳���java.lang.Thread�������ࡣ ���������·�ʽ��java�д���һ���̣߳�
		//һ���߳�������start�����ͻ��������أ�������ȴ���run����ִ����ϲŷ��ء��ͺ���run������������һ��cpu��ִ��һ��
		//����run����ִ�к󣬽����ӡ���ַ���MyThread running��
		//1
		MyThread objThread=new MyThread();
		objThread.start();
		
		//2
		//���µ��̵߳�run����ִ���Ժ󣬼���������ӡ���ַ�����Thread Running����
		Thread thread=new Thread(){
			public void run(){
				System.out.println("ok");
			}
		};
		thread.start();
		
		//���߳�����ʱ�����������ʵ����Runnable�ӿڵ�run����
		//3
		Thread threadThree=new Thread(new MyRunnable());
		threadThree.start();
		
		//��Ϊ�̳߳ؿ�����Ч�Ĺ���ʵ����Runnable�ӿڵ��̣߳�����̳߳����ˣ��µ��߳̾ͻ��ŶӵȺ�ִ�У�ֱ���̳߳ؿ��г���Ϊֹ
		//4
		Runnable objRunable=new Runnable(){
			public void run(){
				System.out.println("ok");
			}
		};
		Thread threadFour = new Thread(objRunable);
		thread.start();
	}
	//��һ�ַ�ʽ
	public static class MyThread extends Thread{
		public void run(){
			System.out.println("runing");
		}
	}
	//�ڶ��ַ�ʽ
	public static class MyRunnable implements Runnable{
		public void run(){
			 System.out.println("MyRunnable running");
		}
	}
}
