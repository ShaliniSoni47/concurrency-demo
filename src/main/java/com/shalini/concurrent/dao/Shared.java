package com.shalini.concurrent.dao;

public class Shared{
	
	private static int count = 5;
	
	public synchronized static void addToCount(int value) {
		count = count+value;
	}

	public static Integer getCount() {
		return count;
	}

}
