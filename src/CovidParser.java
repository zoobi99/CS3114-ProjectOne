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
            // Handle case of missing line
            if (row.equals(",,,,,,,,,")) {
                continue;
            }
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
                if (data[i].length() == 0) {
                    data[i] = "-1";
                }
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
                continue;
            }
            // Checking if there is currently an update with the same name and
            // date
            for (int i = 0; i < localIndex; i++) {
                if (masterList[i].getDate() == update.getDate() && masterList[i]
                    .getState().name().equals(update.getState().name())) {
                    masterList[i] = this.compareEntries(masterList[i], update);
                    continue;
                }
            }
            masterList[localIndex] = update;
            localIndex++;
        }
        csvReader.close();
    }


    private CovidUpdate compareEntries(
        CovidUpdate current,
        CovidUpdate newUpdate) {
        boolean updateFlag = false;

        if (newUpdate.getDataQualityScore() > current.getDataQualityScore()) {
            updateFlag = true;
        }
        int date = current.getDate();
        String state = current.getState().name();
        // Checking for missing values in current and ensures 
        // newUpdate doesn't have missing data
        int positiveCases = current.getPositives();
        int newPositiveCases = newUpdate.getPositives();
        if ((positiveCases == -1 || updateFlag) && newPositiveCases != -1) {
            positiveCases = newUpdate.getPositives();
        }
        int negativeCases = current.getNegatives();
        int newNegativeCases = newUpdate.getNegatives();
        if ((negativeCases == -1 || updateFlag) && newNegativeCases != -1) {
            negativeCases = newUpdate.getNegatives();
        }
        int hospitalized = current.getHospitalized();
        int newHospitalized = newUpdate.getHospitalized();
        if ((hospitalized == -1 || updateFlag) && newHospitalized != -1) {
            hospitalized = newUpdate.getHospitalized();
        }
        int currOnVent = current.getCurrentOnVent();
        int newOnVentilator = newUpdate.getCurrentOnVent();
        if ((currOnVent == -1 || updateFlag) && newOnVentilator != -1) {
            currOnVent = newUpdate.getCurrentOnVent();
        }
        int cumOnVent = current.getCumulativeOnVent();
        int newCumOnVent = newUpdate.getCumulativeOnVent();
        if ((cumOnVent == -1 || updateFlag) && newCumOnVent != -1) {
            cumOnVent = newUpdate.getCumulativeOnVent();
        }
        int currRecovered = current.getRecovered();
        int newRecovered = newUpdate.getRecovered();
        if ((currRecovered == -1 || updateFlag) && newRecovered != -1) {
            currRecovered = newUpdate.getRecovered();
        }
        String grade = current.getDataQualityGrade();
        if (updateFlag) {
            grade = newUpdate.getDataQualityGrade();
        }
        int deaths = current.getDeaths();
        int newDeaths = newUpdate.getDeaths();
        if ((deaths == -1 || updateFlag) && newDeaths != -1) {
            deaths = newUpdate.getDeaths();
        }
        // we will keep the old index
        int updateIndex = current.getIndex();

        CovidUpdate output = new CovidUpdate(date, state, positiveCases,
            negativeCases, hospitalized, currOnVent, cumOnVent, currRecovered,
            grade, deaths, updateIndex);
        return output;
    }


    public CovidUpdate[] getList() {
        return this.masterList;
    }

}
