package cn.itcast.heima2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCacheDeomo {

	public static void main(String[] args) {

	}

	Map<String, Object> map = new HashMap<String, Object>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		rwl.readLock().lock();
		Object data;
		try {
			data = map.get(key);
			if (data == null) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if (data == null) {
						data = "22222";
					}
				} finally {
					rwl.writeLock().unlock();
				}

				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
		}
		return data;
	}
}
