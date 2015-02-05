package com.org.threads;

import com.org.pool.ConnectionPool;


public class ConnectionFollowThread extends Thread{
	private static ConnectionFollowThread thread = null;
	public static ConnectionFollowThread getInstance(){
		if(thread == null){
			thread = new ConnectionFollowThread();
		}
		return thread;
	}

	@Override
	public synchronized void run() {
		while(true){
			try {
				int connectionCounts = ConnectionPool.getInstance().getConnSize();
				System.out.println("当前连接池待使用连接: " + connectionCounts);
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
