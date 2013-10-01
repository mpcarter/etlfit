package etlfit;

import fitlibrary.DoFixture;

import java.io.*;
import java.util.*;

public class RunKettleFixture extends DoFixture {
	private static String osName = System.getProperty("os.name").toLowerCase();
	private String logLevel = "Minimal";
	private String logDirectory = System.getProperty("java.io.tmpdir");
	private String kettleDirectory = (isWindows()) ? "C:\\pentaho\\data-integration\\" : "/opt/pentaho/data-integration/";
	private String trnExecutor = (isWindows()) ? "Pan.bat" : "pan.sh";
	private String jobExecutor = (isWindows()) ? "Kitchen.bat" : "kitchen.sh";
	private String workDirectory = System.getProperty("user.home") + File.separator;
	private String etlDirectory = System.getProperty("user.home") + File.separator;
	private String javaHome = System.getenv("JAVA_HOME");
	private String kettleHome = "";
	private int exitValue;

	private int runProcess(ProcessBuilder pb) {
		int ev;
		try {
			pb.redirectErrorStream(true);
			pb.directory(new File(workDirectory));
			Map<String, String> env = pb.environment();
			env.put("PENTAHO_JAVA_HOME", javaHome);
			if (kettleHome != "") {
				env.put("KETTLE_HOME", kettleHome);
			}
			Process process = pb.start();
			//ev = process.waitFor();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			ev = process.exitValue();

		} catch (IOException ioex) {
			ev = 1;
		//} catch (InterruptedException iex) {
	//		ev = 1;
		}
		return ev;
	}

	private boolean isWindows() {
		return (osName.indexOf("win") >= 0);
	}

	public boolean runTransformationAtWith(String trnName, String trnPath, String[] trnParamArray) {
		List<String> commands = new ArrayList<String>();
		commands.add(kettleDirectory + trnExecutor);
		commands.add("/file:" + trnPath + trnName + ".ktr"); 
		for (String param : trnParamArray) {
			commands.add("/param:" + param);	
		}
		commands.add("/level:" + logLevel); 
		commands.add("/log:" + logDirectory + trnName + ".log");
		
		ProcessBuilder pb = new ProcessBuilder(commands);
		exitValue = runProcess(pb);

		return exitValue == 0;
	}

	public boolean runTransformationWith(String trnName, String[] trnParamArray) {
		return runTransformationAtWith(trnName, etlDirectory, trnParamArray);
	}

	public boolean runTransformationAt(String trnName, String trnPath) {
		String[] trnParamArray;
		trnParamArray = new String[0];
		return runTransformationAtWith(trnName, trnPath, trnParamArray);
	}

	public boolean runTransformation(String trnName) {
		return runTransformationAt(trnName, etlDirectory);
	}

	public boolean runJobAtWith(String jobName, String jobPath, String[] jobParamArray) {
		List<String> commands = new ArrayList<String>();
		commands.add(kettleDirectory + jobExecutor);
		commands.add("/file:" + jobPath + jobName + ".kjb"); 
		for (String param : jobParamArray) {
			commands.add("/param:" + param);	
		}
		commands.add("/level:" + logLevel); 
		commands.add("/log:" + logDirectory + jobName + ".log");
		
		ProcessBuilder pb = new ProcessBuilder(commands);
		exitValue = runProcess(pb);

		return exitValue == 0;
	}

	public boolean runJobWith(String jobName, String[] jobParamArray) {
		return runJobAtWith(jobName, etlDirectory, jobParamArray);
	}

	public boolean runJobAt(String jobName, String jobPath) {
		String[] jobParamArray;
		jobParamArray = new String[0];
		return runJobAtWith(jobName, jobPath, jobParamArray);
	}

	public boolean runJob(String jobName) {
		return runJobAt(jobName, etlDirectory);
	}

	public void setLogLevel(String level) {
		logLevel = level;
	}

	public void setLogDirectory(String dir) {
		logDirectory = dir;
	}

	public void setEtlDirectory(String dir) {
		etlDirectory = dir;
	}
	
	public void setKettleDirectory(String dir) {
		kettleDirectory = dir;
	}
	
	public void setJavaHome(String dir) {
		javaHome = dir;
	}

	public void setKettleHome(String dir) {
		kettleHome = dir;
	}

	public void setWorkDirectory(String dir) {
		if (dir.equalsIgnoreCase("default")) {
			workDirectory = System.getProperty("user.home") + File.separator;
		} else {
			workDirectory = dir;
		}
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

