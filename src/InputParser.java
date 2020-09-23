import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputParser {
    
    private ArrayList<CovidUpdate> masterList;

    public InputParser(String inputFileName) throws IOException {

        File file = new File(inputFileName);
        String row = "";
        BufferedReader txtReader = new BufferedReader(new FileReader(file));
        masterList = null;

        while ((row = txtReader.readLine()) != null) {
            
            // Trims line
            row = row.replaceAll("\\s{2,}", " ").trim();
            System.out.println(row);
            String[] splitted = row.split("\\s+");
            if (splitted[0].equals("load")) {
                this.loadCommand(splitted);
            }
            else if (splitted[0].equals("search")) {
                this.searchCommand(splitted);
            }
        }

        txtReader.close();
    }

    private void searchCommand(String[] splitted) {
        if (splitted.length >= 3) {
            State searchState = null;
            
            int numRecords = -1;
            if (splitted.length == 3) {
                searchState = this.getStateEnum(splitted[1]);
                numRecords = Integer.valueOf(splitted[2]);
                Searcher searcher = new Searcher(masterList, searchState, numRecords, splitted[1]);
            }
            else {
                searchState = this.getStateEnum(splitted[1] + " " + splitted[2]);
                numRecords = Integer.valueOf(splitted[3]);
            }
        }
    }

    private void loadCommand(String[] splitted) {
        try {
            String dataFile = splitted[1];
            CovidParser parser = new CovidParser(dataFile, masterList);
            masterList = parser.getList();
            System.out.println("Finished loading " + dataFile + " file");
            System.out.println(parser.getMostRecentRecordsAdded() + " records have been loaded");
        }
        catch (Exception E) {
            System.out.println("Improper use of load command");
        }
    }

    public ArrayList<CovidUpdate> getMasterList() {
        return masterList;
        
    }
    
    public State getStateEnum(String stateName) {
        if (stateName.equalsIgnoreCase("Alabama") || stateName.equalsIgnoreCase("AL")) {
            return State.AL;
        }
        if (stateName.equalsIgnoreCase("Alaska") || stateName.equalsIgnoreCase("AK")) {
            return State.AK;
        }
        if (stateName.equalsIgnoreCase("Arizona") || stateName.equalsIgnoreCase("AZ")) {
            return State.AZ;
        }
        if (stateName.equalsIgnoreCase("Arkansas") || stateName.equalsIgnoreCase("AR")) {
            return State.AR;
        }
        if (stateName.equalsIgnoreCase("California")  || stateName.equalsIgnoreCase("CA")) {
            return State.CA;
        }
        if (stateName.equalsIgnoreCase("Conneticut") || stateName.equalsIgnoreCase("CT")) {
            return State.CT;
        }
        if (stateName.equalsIgnoreCase("Delaware") || stateName.equalsIgnoreCase("DE")) {
            return State.DE;
        }
        if (stateName.equalsIgnoreCase("New Hampshire") || stateName.equalsIgnoreCase("NH")) {
            return State.NH;
        }
        if (stateName.equalsIgnoreCase("New Jersey") || stateName.equalsIgnoreCase("NJ")) {
            return State.NJ;
        }
        if (stateName.equalsIgnoreCase("Oregon") || stateName.equalsIgnoreCase("OR")) {
            return State.OR;
        }
        if (stateName.equalsIgnoreCase("Kentucky") || stateName.equalsIgnoreCase("KY")) {
            return State.KY;
        }
        return null;
    }
}
