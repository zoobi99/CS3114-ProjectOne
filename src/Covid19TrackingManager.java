import java.io.File;
import java.lang.IllegalArgumentException;


public class Covid19TrackingManager {

	private static String input;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Input should only contain filename");
		}
		try {
			input = args[0];
			
		}
		catch (Exception e) {
			System.out.println("File was not found");
		}
	}
}
