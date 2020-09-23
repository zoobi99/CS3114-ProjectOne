import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CovidParser {

    private int localIndex;
    private ArrayList<CovidUpdate> masterList;


    public CovidParser(String commandFileName, ArrayList<CovidUpdate> list) throws IOException {
        // Accounting for if load has or hasnt been called before
        if (list==null) {
            masterList = new ArrayList<CovidUpdate>();
        }
        else {
            masterList = list;
        }
        File file = new File(commandFileName);
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        csvReader.readLine();
        localIndex = 0;
        // Pulling off each row of data
        while ((row = csvReader.readLine()) != null) {
            // Handle case of missing line
            if (row.equals(",,,,,,,,,")) {
                System.out.println("Blank line!");
                continue;
            }
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
                // Substitute in -1 for missing data
                if (data[i].length() == 0) {
                    data[i] = "-1";
                }
            }
            // Missing state date or data quality grade
            if (data[1] == "-1" || data[0] == "-1" || data[8] == "-1") {
                System.out.println("Discard invalid record");
                continue;
            }
            CovidUpdate update = new CovidUpdate(Integer.parseInt(data[0]),
                data[1], (int)Double.parseDouble(data[2]), (int)Double
                    .parseDouble(data[3]), (int)Double.parseDouble(data[4]),
                (int)Double.parseDouble(data[5]), (int)Double.parseDouble(
                    data[6]), (int)Double.parseDouble(data[7]), data[8],
                (int)Double.parseDouble(data[9]), localIndex);
            // Checking if state is valid, will continue to next iteration if
            // not
            if (!update.checkState(data[1], false)) {
                localIndex++;
                continue;
            }
            boolean matchExists = false;
            // Checking if there is currently an update with the same name and
            // date
            for (int i = 0; i < masterList.size(); i++) {
                if (masterList.get(i).getDate() == update.getDate() && masterList.get(i)
                    .getState().name().equals(update.getState().name())) {
                    masterList.set(i, this.compareEntries(masterList.get(i), update));
                    matchExists = true;
                }
            }
            // If we found a matching record and already updated, we dont want to add anything
            if (matchExists) {
                continue;
            }
            masterList.add(update);
            localIndex++;
        }
        csvReader.close();
        // Sort masterlist after adding new entries
        Collections.sort(masterList, new Comparator<CovidUpdate>() {
            @Override
            public int compare(CovidUpdate o1, CovidUpdate o2) {
                return Integer.compare(o2.getDate(), o1.getDate());
            }

        });
    }


    private CovidUpdate compareEntries(
        CovidUpdate current,
        CovidUpdate newUpdate) {
        boolean updateFlag = false;
        boolean changeTracker = false;
        
        if (newUpdate.getDataQualityScore() > current.getDataQualityScore()) {
            updateFlag = true;
            System.out.println("Data has been updated for " + newUpdate.getState() + 
                    " " + newUpdate.getDate());
        }
        int date = current.getDate();
        String state = current.getState().name();
        // Checking for missing values in current and ensures 
        // newUpdate doesn't have missing data
        int positiveCases = current.getPositives();
        int newPositiveCases = newUpdate.getPositives();
        // Checking if change made due to missing data
        if (!updateFlag && positiveCases == -1 && newPositiveCases != -1) {
            changeTracker = true;
        }
        if (positiveCases == -1 || updateFlag) {
            positiveCases = newUpdate.getPositives();
        }
        int negativeCases = current.getNegatives();
        int newNegativeCases = newUpdate.getNegatives();
        if (!updateFlag && negativeCases == -1 && newNegativeCases != -1) {
            changeTracker = true;
        }
        if (negativeCases == -1 || updateFlag) {
            negativeCases = newUpdate.getNegatives();
        }
        int hospitalized = current.getHospitalized();
        int newHospitalized = newUpdate.getHospitalized();
        if (!updateFlag && hospitalized == -1 && newHospitalized != -1) {
            changeTracker = true;
        }
        if (hospitalized == -1 || updateFlag) {
            hospitalized = newUpdate.getHospitalized();
        }
        int currOnVent = current.getCurrentOnVent();
        int newOnVentilator = newUpdate.getCurrentOnVent();
        if (!updateFlag && currOnVent == -1 && newOnVentilator != -1) {
            changeTracker = true;
        }
        if (currOnVent == -1 || updateFlag) {
            currOnVent = newUpdate.getCurrentOnVent();
        }
        int cumOnVent = current.getCumulativeOnVent();
        int newCumOnVent = newUpdate.getCumulativeOnVent();
        if (!updateFlag && cumOnVent == -1 && newCumOnVent != -1) {
            changeTracker = true;
        }
        if (cumOnVent == -1 || updateFlag) {
            cumOnVent = newUpdate.getCumulativeOnVent();
        }
        int currRecovered = current.getRecovered();
        int newRecovered = newUpdate.getRecovered();
        if (!updateFlag && currRecovered == -1 && newRecovered != -1) {
            changeTracker = true;
        }
        if (currRecovered == -1 || updateFlag) {
            currRecovered = newUpdate.getRecovered();
        }
        String grade = current.getDataQualityGrade();
        if (updateFlag) {
            grade = newUpdate.getDataQualityGrade();
        }
        int deaths = current.getDeaths();
        int newDeaths = newUpdate.getDeaths();
        if (!updateFlag & deaths == -1 && newDeaths != -1) {
            changeTracker = true;
        }
        if (deaths == -1 || updateFlag) {
            deaths = newUpdate.getDeaths();
        }
        // we will keep the old index
        int updateIndex = current.getIndex();
        
        if (changeTracker) {
            System.out.println("Data has been updated for the missing data in " + 
                    current.getState());
        }
        CovidUpdate output = new CovidUpdate(date, state, positiveCases,
            negativeCases, hospitalized, currOnVent, cumOnVent, currRecovered,
            grade, deaths, updateIndex);
        System.out.println("Low quality data rejected for " + output.getState());
        return output;
    }


    public ArrayList<CovidUpdate> getList() {
        return this.masterList;
    }
    
    public int getMostRecentRecordsAdded() {
        return localIndex;
    }

}
