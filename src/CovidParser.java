import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CovidParser {

	private CovidUpdate[] masterList;
	private int localIndex;
	
	public CovidParser(String commandFileName) throws IOException {
	
		masterList = new CovidUpdate[100];
		File file = new File(commandFileName);
		String row = "";
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
        csvReader.readLine();
        localIndex = 0;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
            	if (data[i].length() == 0) {
            		data[i] = "0";
            	}
            }
            masterList[localIndex] = new CovidUpdate(Integer.parseInt(data[0]), data[1],
            		(int)Double.parseDouble(data[2]), (int)Double.parseDouble(data[3]), 
            		(int)Double.parseDouble(data[4]), (int)Double.parseDouble(data[5]), 
            		(int)Double.parseDouble(data[6]), (int)Double.parseDouble(data[7]),
            		data[8], (int)Double.parseDouble(data[9]), localIndex); 
            localIndex++;
        }
        csvReader.close();
	}
	
	public CovidUpdate[] getList() {
		return this.masterList;
	}
}
