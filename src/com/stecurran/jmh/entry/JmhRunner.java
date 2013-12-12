package com.stecurran.jmh.entry;

import org.openjdk.jmh.logic.results.Result;
import org.openjdk.jmh.logic.results.RunResult;
import org.openjdk.jmh.output.OutputFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.parameters.TimeValue;

public class JmhRunner {

	// private static final String TEST =
	// "com.stecurra.benchmark.strategy.EventRunner";

	public static void main(String[] args) throws RunnerException {

		Options ops = new OptionsBuilder().include(".*").warmupTime(TimeValue.milliseconds(200)).measurementTime(TimeValue.milliseconds(100)).outputFormat(OutputFormatType.TextReport).build();
		RunResult run = new Runner(ops).runSingle();
		Result result = run.getPrimaryResult();

		System.out.println();
		System.out.println("API replied benchmark score: " + result.getScore() + " " + result.getScoreUnit() + " over " + result.getStatistics().getN() + " iterations");
		

	}

	/*
	 * 
	 * private static String[] getArguments(String className, int nRuns, int
	 * runForMilliseconds, int nThreads) { Main.main(getArguments(TEST, 5, 5000,
	 * 1)); return new String[] { className, "-i", "" + nRuns, "-r",
	 * runForMilliseconds + "ms", "-t", "" + nThreads, "-w", "5000ms", "-wi",
	 * "3", "-v" }; }
	 */
}
