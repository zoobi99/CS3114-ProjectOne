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
        // Pulling off each row of data
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
            	if (data[i].length() == 0) {
            		data[i] = "0";
            	}
            }
            CovidUpdate update = new CovidUpdate(Integer.parseInt(data[0]), data[1],
            		(int)Double.parseDouble(data[2]), (int)Double.parseDouble(data[3]), 
            		(int)Double.parseDouble(data[4]), (int)Double.parseDouble(data[5]), 
            		(int)Double.parseDouble(data[6]), (int)Double.parseDouble(data[7]),
            		data[8], (int)Double.parseDouble(data[9]), localIndex);
            // Checking if state is valid, will continue to next iteration if not
            if (!update.checkState(data[1], false)) {
            	continue;
            }
            // Checking if there is currently an update with the same name and date
            for (int i = 0; i < localIndex; i++) {
            	if (masterList[i].getDate() == update.getDate() && 
            			masterList[i].getState().name().equals(update.getState().name())) {
            		System.out.println("Same!!!");
            	}
            }
            masterList[localIndex] = update;
            localIndex++;		
        }
        csvReader.close();
	}
	
	public CovidUpdate[] getList() {
		return this.masterList;
	}
}
