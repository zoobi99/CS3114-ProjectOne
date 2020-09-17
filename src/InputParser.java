import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputParser {

    public InputParser(String inputFileName) throws IOException {

        File file = new File(inputFileName);
        String row = "";
        BufferedReader txtReader = new BufferedReader(new FileReader(file));

        while ((row = txtReader.readLine()) != null) {
            
            //handles leading and trailing white space
            row = row.trim();
            //handles excessive whitespace between words
            row = row.replaceAll("\\s+", " ");

            // this print line is to help with testing
            // Remove when done with project
            System.out.println(row);

        }

        txtReader.close();
    }

}
