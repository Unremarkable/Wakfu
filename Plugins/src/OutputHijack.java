import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class OutputHijack implements Plugin {
	PrintStream out;
	PrintStream err;
	
	PrintStream oldOut;
	PrintStream oldErr;
	
	@Override
	public void load() {
		try {
			oldOut = System.out;
			oldErr = System.err;
			
			File outFile = new File("out.log");
			File errFile = new File("err.log");

			if (!outFile.exists()) outFile.createNewFile();
			if (!errFile.exists()) errFile.createNewFile();

			out = new PrintStream(outFile);
			err = new PrintStream(outFile);
			
			System.setOut(out);
			System.setErr(err);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	@Override
	public void unload() {
		try {
			out.close();
			err.close();
		} finally {
			System.setOut(oldOut);
			System.setErr(oldErr);
		}
	}

	@Override
	public void exec(String args) {
		System.out.println(args);
	}

	@Override
	public String getName() {
		return "OutputHijack";
	}
}
