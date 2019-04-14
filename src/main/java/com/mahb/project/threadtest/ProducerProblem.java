package com.mahb.project.threadtest;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;


public class ProducerProblem {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Container container = new Container();

		Future produceFuture = executorService.submit(()->{ // 生产线程
			container.produce();
		});
		
		// Future 会让程序 从并行 变  串行
		Future consumerFuture =	executorService.submit(()->{ // 消费线程
			container.consume();
		});
		
		Thread.sleep(1000L);
		
		// 阻塞  等待
		//produceFuture.get();
		//consumerFuture.get();
		
		executorService.shutdown();
		
		Lock lock = new ReentrantLock();
		
		// 条件变量包括条件，同时 它又是线程通讯媒介
		Condition condition = lock.newCondition();
		
		new StampedLock().tryOptimisticRead(); // 优化锁 可以读  缓存
		
		
		try {
			if(lock.tryLock(3,TimeUnit.SECONDS)) { // 当且仅当在规定的时间内获取锁
				// TODO
			}
		} catch (InterruptedException e) {
			// 重置 中止状态 （防止被 中途  清除 interrupt 状态 ）
			Thread.currentThread().interrupt(); 
		}
		
		
		
		
		
	}
	
	public static class Container {
		
		private List<Integer> data = new LinkedList<Integer>(); 
		
		private final int MAX_SIZE = 5;
		private Random random = new Random();
		
		public void produce(){
		
			while (true) { // 永久执行
				synchronized (this) {
					try {
						// 当数据超过上限，停止生产
						while( data.size() >= MAX_SIZE ){
							wait();
							
						}
						int value = random.nextInt(100);
						System.out.printf("线程[%s] 正在生产数据：%d \n", Thread.currentThread().getName(), value);
						data.add(value);
						
						// 唤起消费线程
						notify();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		public void consume(){
			while (true) { // 永久执行
				synchronized (this) {
					try {
						// 当没有数据时， 进行等待；
						while( data.isEmpty() ){
							wait();
							
						}
						int value = data.remove(0); 
						System.out.printf("线程[%s] 正在消费数据：%d -------------\n", Thread.currentThread().getName(), value);
						
						notify();
						Thread.sleep(1000);
					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
