package codeforces;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileMaker {

	public static void buildFile(String fileName, String text) throws IOException{
		FileOutputStream out = new FileOutputStream(fileName);
		out.write(text.getBytes());
		out.close();
	}
}
