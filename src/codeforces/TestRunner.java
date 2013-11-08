package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import diff.OutputComparison;

public class TestRunner implements Runnable {

	public static final int DEFAULT_TIME_LIMIT = 3000; // in milliseconds
	private static final int OUTPUT_MAX_LEN = 100 * 1024; // 100 kb

	private TestResult testResult;
	private Process process;
	private String pathToExecFile;
	private String input;
	private String output;
	
	public TestRunner(String pathToExecFile, String input, String output) {
		this.testResult = new TestResult(ExecutionResult.NOT_RUN);
		this.pathToExecFile = pathToExecFile;
		this.input = input;
		this.output = output;
	}

	public void setExecutionResult(ExecutionResult result) {
		this.testResult.result = result;
	}

	
	public void kill() {
		try {
			process.exitValue();
		} catch (IllegalThreadStateException ex) {
			process.destroy();
		}
	}

	public TestResult getResult() {
		return this.testResult;
	}

	@Override
	public void run() {
		try {
			BufferedReader br;
			StringBuilder sb;
			int len;
			char[] buf = new char[1024];
			setExecutionResult(ExecutionResult.RUNNING);

			process = Runtime.getRuntime().exec(pathToExecFile);

			OutputStream stdin = process.getOutputStream();
			InputStream stdout = process.getInputStream();

			// writing the input to stdin
			stdin.write(input.getBytes());
			stdin.close();

			// reading the program output from stdout
			br = new BufferedReader(new InputStreamReader(stdout));
			sb = new StringBuilder();
			while ((len = br.read(buf)) != -1 && sb.length() < OUTPUT_MAX_LEN) {
				sb.append(buf, 0, len);
			}
			if (sb.length() >= OUTPUT_MAX_LEN) {
				this.testResult.result = ExecutionResult.RE;
				this.testResult.detail = "Output limit exceeded";
				kill();
				throw new IOException("Output limit exceeded");
			}
			String programOutput = sb.toString();
			br.close();

			int ret = process.waitFor();
			if (ret != 0) {
				if (this.testResult.result == ExecutionResult.RUNNING){
					setExecutionResult(ExecutionResult.RE);
				}
			} else {
				String compareResult = OutputComparison.diff(output, programOutput);
				if (this.testResult.result == ExecutionResult.RUNNING) {
					if (compareResult.equals("OK")) {
						this.testResult.result = ExecutionResult.AC;
					} else {
						this.testResult.result = ExecutionResult.WA;
						this.testResult.detail = compareResult;
					}
				}
			}
		} catch (IOException e) {
			// file not found
			setExecutionResult(ExecutionResult.RE);
		} catch (Exception e) {
			// normal interupt for TLE codes
		}
	}
}
