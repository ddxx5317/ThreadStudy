package com;

import java.util.Random;

public class ThreadLocalTest {

	public static void main(String[] args) {
		for(int i=0;i<3;i++){
			new Thread(new Runnable(){
				public void run() {
					int data = new Random().nextInt(100);
					System.out.println(Thread.currentThread().getName() + " has put data :" + data);
					MyThreadScopeData.getThreadInstance().setName("name" + data);
					MyThreadScopeData.getThreadInstance().setAge(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
			System.out.println("A from " + Thread.currentThread().getName() + " getMyData: " + myData.getName() + "," +
					myData.getAge());
		}
	}
	
	static class B{
		public void get(){
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());			
		}		
	}
}
