import java.io.File;
import java.lang.IllegalArgumentException;
//main class
public class Covid19TrackingManager {

    private static String input;
    private static InputParser inputParser;


    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                "Input should only contain file name");
        }
        try {
            input = args[0];
            inputParser = new InputParser(input);

        }
        catch (Exception e) {
            System.out.println("File was not found");
        }
    }
}
