package com.stecurra.benchmark.strategy;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;



@BenchmarkMode(Mode.AverageTime)
public class EventRunner {
	
	@GenerateMicroBenchmark
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void runTest(){
		// FacebookRetriever fbCal = FacebookRetriever.getInstance();
		GoogleRetriever gCal = GoogleRetriever.getInstance();

		CalendarService cs = new CalendarService(gCal);

		for (SimpleEvent simpleEvent : cs.getEvents()) {
			System.out.println(simpleEvent);
		}
		
	}

}
