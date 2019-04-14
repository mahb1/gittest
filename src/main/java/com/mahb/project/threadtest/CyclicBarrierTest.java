package com.mahb.project.threadtest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		
		/*
		 * CyclicBarrier cyclicBarrier = new CyclicBarrier(4); for (int i = 0; i < 4;
		 * i++) { new Writer(cyclicBarrier).start(); }
		 */
	
		
		 CyclicBarrierTest c = new CyclicBarrierTest(); 
		 c.isshow(10*1000, 5);
	}
	int count =0;
	int result = 0;
	public void isshow(int n, int k) {
		
		  count++; 
		  if(k == 0 && n>9) {
			  int re = isAccumn(String.valueOf(n));
			  System.out.println("第"+count+"次拼接的值"+re); result = result+re; isshow(re, 0);
		  }else if(k == 0 ) {
			  System.out.println("执行完之后的......."+result+"。。。。。。。。。"+count); 
		  }
		
		if(k != 0) {
			n--;
			StringBuilder builder = new StringBuilder(String.valueOf(n));		
			System.out.println("n--....."+builder);
			for (int i = 1; i < k; i++) {
				builder.append(n);
			}
			int re = isAccumn(builder.toString());
			System.out.println("第一次拼接的值"+re);
			result = result+re;
			isshow(re, 0);
		}
	}
	
	private int isAccumn(String n) {
		System.out.println("n......"+n);
		int re = 0;
		for (int i =1;i<n.length(); i++) { 
			re+= Character.getNumericValue(n.charAt(i));
		}
		return re;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	static class Writer extends Thread {
		private CyclicBarrier barrier ;
		public Writer(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
			
			System.out.println("线程"+Thread.currentThread().getName()+"长在写入数据.....");
			
			try {
				Thread.sleep(5000);
				System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕...");
				barrier.await();
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}catch (BrokenBarrierException e) {
				// TODO: handle exception
			}
			System.out.println("所有数据 写入完毕.......");
		
		}
		
	}
	
	

}
