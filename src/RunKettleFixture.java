package etlfit;

import fitlibrary.DoFixture;

import java.io.*;
import java.util.*;

public class RunKettleFixture extends DoFixture {
	private String logLevel = "Basic";
	private String logDirectory = "/tmp/";
	private String kettleDirectory = "/opt/pentaho/data-integration/";
	private String trnExecutor = "pan.sh";
	private String jobExecutor = "kitchen.sh";
	private int exitValue;

	private int runProcess(ProcessBuilder pb) throws IOException, InterruptedException {
		Process process = pb.start();
		return process.waitFor();
	}

	public boolean runTransformationAtWith(String trnName, String trnPath, String[] trnParams) {

	}

	public boolean runTransformationWith(String trnPath, String[] trnParams) {

	}

	public boolean runTransformationAt(String trnName, String trnPath) {
		ProcessBuilder pb = new ProcessBuilder(kettleDirectory + trnExecutor, 
				"/file:" + trnPath + ".ktr", 
				"/level:" + logLevel, 
				"/log:" + logDirectory + logFile + ".log");
		exitValue = runProcess(pb);

		return exitValue == 0;
	}

	public boolean runTransformation(String trnPath) throws IOException, InterruptedException {
		String trnName;
		if (trnPath.lastIndexOf("/") >= 0) {
			trnName = trnPath.substring(trnPath.lastIndexOf("/"));
			trnPath = trnPath.substring(0, trnPath.lastIndexOf("/") + 1);
		} else {
			trnName = trnPath;
			trnPath = "";
		}
	}

	public void setLogLevel(String level) {
		logLevel = level;
	}

	public void setLogDirectory(String dir) {
		logDirectory = dir;
	}

	public void setKettleDirectory(String dir) {
		kettleDirectory = dir;
	}

	public String getExitValue() {
		String exitMessage;
		switch (exitValue) {
			case 0: exitMessage = "The transformation/job ran without a problem";
				break;
			case 1: exitMessage = "Errors occured during processing";
				break;
			case 2: exitMessage = "An unexpected error occurred during loading / running of the transformation/job";
				break;
			case 3: exitMessage = "Unable to prepare and initialize this transformation";
				break;
			case 7: exitMessage = "The transformation/job could not be loaded from XML or the Repository";
				break;
			case 8: exitMessage = "Error loading steps or plugins";
				break;
			case 9: exitMessage = "Command line usage printing";
				break;
			default: exitMessage = "n/a";
				 break;
		}
		return Integer.toString(exitValue) + ": " + exitMessage;
	}
}

