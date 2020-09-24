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
                String state = splitted[1] + " " + splitted[2];
                Searcher searcher = new Searcher(masterList, searchState, numRecords, state);
                
            }
        }
        // Added***
        String date = null;
        //State searchState = null;
        //int numRecords = -1;
        if (splitted.length == 2) {
            date = splitted[1];
            Searcher search = new Searcher(masterList, date);
        }
        
        //if (splitted.length == 4) {
          //  searchState = this.getStateEnum(splitted[1] + " " + splitted[2]);
         //   numRecords = Integer.valueOf(splitted[3])
         //   Searcher searcher = new Searcher(masterList, searchState, numRecords, splitted[1]);;
        //}
        // Until here ***
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
        if (stateName.equalsIgnoreCase("Colorado") || stateName.equalsIgnoreCase("CO")) {
            return State.CO;
        }
        if (stateName.equalsIgnoreCase("Connecticut") || stateName.equalsIgnoreCase("CT")) {
            return State.CT;
        }
        if (stateName.equalsIgnoreCase("Delaware") || stateName.equalsIgnoreCase("DE")) {
            return State.DE;
        }
        if (stateName.equalsIgnoreCase("Florida") || stateName.equalsIgnoreCase("FL")) {
            return State.FL;
        }
        if (stateName.equalsIgnoreCase("Georgia") || stateName.equalsIgnoreCase("GA")) {
            return State.GA;
        }
        if (stateName.equalsIgnoreCase("Hawaii") || stateName.equalsIgnoreCase("HI")) {
            return State.HI;
        }
        if (stateName.equalsIgnoreCase("Idaho") || stateName.equalsIgnoreCase("ID")) {
            return State.ID;
        }
        if (stateName.equalsIgnoreCase("Illinois") || stateName.equalsIgnoreCase("IL")) {
            return State.IL;
        }
        if (stateName.equalsIgnoreCase("Indiana") || stateName.equalsIgnoreCase("IN")) {
            return State.IN;
        }
        if (stateName.equalsIgnoreCase("Iowa") || stateName.equalsIgnoreCase("IA")) {
            return State.IA;
        }
        if (stateName.equalsIgnoreCase("Kansas") || stateName.equalsIgnoreCase("KS")) {
            return State.KS;
        }
        if (stateName.equalsIgnoreCase("Kentucky") || stateName.equalsIgnoreCase("KY")) {
            return State.KY;
        }
        if (stateName.equalsIgnoreCase("Louisiana") || stateName.equalsIgnoreCase("LA")) {
            return State.LA;
        }
        if (stateName.equalsIgnoreCase("Maine") || stateName.equalsIgnoreCase("ME")) {
            return State.ME;
        }
        if (stateName.equalsIgnoreCase("Maryland") || stateName.equalsIgnoreCase("MD")) {
            return State.MD;
        }
        if (stateName.equalsIgnoreCase("Massachusetts") || stateName.equalsIgnoreCase("MA")) {
            return State.MA;
        }
        if (stateName.equalsIgnoreCase("Michigan") || stateName.equalsIgnoreCase("MI")) {
            return State.MI;
        }
        if (stateName.equalsIgnoreCase("Minnesota") || stateName.equalsIgnoreCase("MN")) {
            return State.MN;
        }
        if (stateName.equalsIgnoreCase("Mississippi") || stateName.equalsIgnoreCase("MS")) {
            return State.MS;
        }
        if (stateName.equalsIgnoreCase("Missouri") || stateName.equalsIgnoreCase("MO")) {
            return State.MO;
        }
        if (stateName.equalsIgnoreCase("Montana") || stateName.equalsIgnoreCase("MT")) {
            return State.MT;
        }
        if (stateName.equalsIgnoreCase("Nebraska") || stateName.equalsIgnoreCase("NE")) {
            return State.NE;
        }
        if (stateName.equalsIgnoreCase("Nevada") || stateName.equalsIgnoreCase("NV")) {
            return State.NV;
        }
        if (stateName.equalsIgnoreCase("New Hampshire") || stateName.equalsIgnoreCase("NH")) {
            return State.NH;
        }
        if (stateName.equalsIgnoreCase("New Jersey") || stateName.equalsIgnoreCase("NJ")) {
            return State.NJ;
        }
        if (stateName.equalsIgnoreCase("New Mexico") || stateName.equalsIgnoreCase("NM")) {
            return State.NM;
        }
        if (stateName.equalsIgnoreCase("New York") || stateName.equalsIgnoreCase("NY")) {
            return State.NY;
        }
        if (stateName.equalsIgnoreCase("North Carolina") || stateName.equalsIgnoreCase("NC")) {
            return State.NC;
        }
        if (stateName.equalsIgnoreCase("North Dakota") || stateName.equalsIgnoreCase("ND")) {
            return State.ND;
        }
        if (stateName.equalsIgnoreCase("Ohio") || stateName.equalsIgnoreCase("OH")) {
            return State.OH;
        }
        if (stateName.equalsIgnoreCase("Oklahoma") || stateName.equalsIgnoreCase("OK")) {
            return State.OK;
        }
        if (stateName.equalsIgnoreCase("Oregon") || stateName.equalsIgnoreCase("OR")) {
            return State.OR;
        }
        if (stateName.equalsIgnoreCase("Pennsylvania") || stateName.equalsIgnoreCase("PA")) {
            return State.PA;
        }
        if (stateName.equalsIgnoreCase("Rhode Island") || stateName.equalsIgnoreCase("RI")) {
            return State.RI;
        }
        if (stateName.equalsIgnoreCase("South Carolina") || stateName.equalsIgnoreCase("SC")) {
            return State.SC;
        }
        if (stateName.equalsIgnoreCase("South Dakota") || stateName.equalsIgnoreCase("SD")) {
            return State.SD;
        }
        if (stateName.equalsIgnoreCase("Tennessee") || stateName.equalsIgnoreCase("TN")) {
            return State.TN;
        }
        if (stateName.equalsIgnoreCase("Texas") || stateName.equalsIgnoreCase("TX")) {
            return State.TX;
        }
        if (stateName.equalsIgnoreCase("Utah") || stateName.equalsIgnoreCase("UT")) {
            return State.UT;
        }
        if (stateName.equalsIgnoreCase("Vermont") || stateName.equalsIgnoreCase("VT")) {
            return State.VT;
        }
        if (stateName.equalsIgnoreCase("Virginia") || stateName.equalsIgnoreCase("VA")) {
            return State.VA;
        }
        if (stateName.equalsIgnoreCase("Washington") || stateName.equalsIgnoreCase("WA")) {
            return State.WA;
        }
        if (stateName.equalsIgnoreCase("West Virginia") || stateName.equalsIgnoreCase("WV")) {
            return State.WV;
        }
        if (stateName.equalsIgnoreCase("Wisconsin") || stateName.equalsIgnoreCase("WI")) {
            return State.WI;
        }
        if (stateName.equalsIgnoreCase("Wyoming") || stateName.equalsIgnoreCase("WY")) {
            return State.WY;
        }
        if (stateName.equalsIgnoreCase("American Samoa") || stateName.equalsIgnoreCase("AS")) {
            return State.AS;
        }
        if (stateName.equalsIgnoreCase("District of Columbia") || stateName.equalsIgnoreCase("DC")) {
            return State.WY;
        }
        if (stateName.equalsIgnoreCase("Federated States of Micronesia") || stateName.equalsIgnoreCase("FM")) {
            return State.FM;
        }
        if (stateName.equalsIgnoreCase("Guam") || stateName.equalsIgnoreCase("GU")) {
            return State.GU;
        }
        if (stateName.equalsIgnoreCase("Marshall Islands") || stateName.equalsIgnoreCase("MH")) {
            return State.MH;
        }
        if (stateName.equalsIgnoreCase("Northern Mariana Islands") || stateName.equalsIgnoreCase("MP")) {
            return State.MP;
        }
        if (stateName.equalsIgnoreCase("Palau") || stateName.equalsIgnoreCase("PW")) {
            return State.PW;
        }
        if (stateName.equalsIgnoreCase("Puerto Rico") || stateName.equalsIgnoreCase("PR")) {
            return State.PR;
        }
        if (stateName.equalsIgnoreCase("Virgin Islands") || stateName.equalsIgnoreCase("VI")) {
            return State.VI
                ;
        }
    
        
        return null;
    }
}
