package com.yh.core.util;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成订单编号并清理过期编号
 */
public class SerialUtil {

	static ConcurrentHashMap<Long, Integer> orderSerialMap = new ConcurrentHashMap<Long, Integer>();

	private SerialUtil() {

	}

	public static String generateOrderSerial(String node) {

		long ms = System.currentTimeMillis();
		double sec = Math.ceil(ms / 100);
		BigDecimal bd = new BigDecimal(sec);
		bd.setScale(0);
		Long key = bd.longValue();

		Integer sortNo = orderSerialMap.get(key);
		if (sortNo == null) {
			CleanMap clean = new CleanMap(key);
			clean.run();

			sortNo = 0;
		}

		sortNo += 1;
		StringBuffer sb = new StringBuffer();
		sb.append(node).append(key);
		if (sortNo < 10) {
			sb.append("00");
		} else if (sortNo < 100) {
			sb.append("0");
		} else if (sortNo >= 1000) {
			sb.append("00");
			sortNo = 0;
		}

		sb.append(sortNo);

		orderSerialMap.put(key, sortNo);

		return sb.toString();
	}

}

class CleanMap implements Runnable {

	private Long key;

	CleanMap(Long key) {
		this.key = key;
	}

	@Override
	public void run() {
		Iterator<Entry<Long, Integer>> it = SerialUtil.orderSerialMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Long, Integer> entry = it.next();
			Long oldKey = entry.getKey();
			if (oldKey == null || oldKey > key) {
				continue;
			}
			SerialUtil.orderSerialMap.remove(oldKey);
		}
	}

}
