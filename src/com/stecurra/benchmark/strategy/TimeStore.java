package com.stecurra.benchmark.strategy;

public class TimeStore {
	
	public static long start = 0l;
	public static long end = 0l;
	
	public static long getTime(){
		return end - start;
	}

}
