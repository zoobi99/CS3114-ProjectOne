import java.util.ArrayList;

public class Searcher {

    private ArrayList<CovidUpdate> list;
    int numRecords = 0;
    State state = null;
    int date = 0;
    String originalInput;
    private ArrayList<CovidUpdate> result;
    
    public Searcher(ArrayList<CovidUpdate> list, State searchState, int numRecords, 
            String input) {
        this.list = list;
        this.state = searchState;
        this.numRecords = numRecords;
        this.originalInput = input;
        int instances = 0;
        result = new ArrayList<CovidUpdate>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getState().equals(state)) {
                instances++;
            }
        }
        // When at least the number of instances are found
        if (instances >= numRecords) {
            for (int i = 0; i < numRecords; i++) {
                if (list.get(i).getState().equals(state)) {
                    CovidUpdate newUpdate = new CovidUpdate(list.get(i).getDate(), list.get(i).getState().name(), 
                            list.get(i).getPositives(), list.get(i).getNegatives(), list.get(i).getHospitalized(),
                            list.get(i).getCurrentOnVent(), list.get(i).getCumulativeOnVent(), list.get(i).getRecovered(),
                            list.get(i).getDataQualityGrade(), list.get(i).getDeaths(), i);
                    result.add(newUpdate);
                }
            }
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getState().name().equals(state.name())) {
                    CovidUpdate newUpdate = new CovidUpdate(list.get(i).getDate(), list.get(i).getState().name(), 
                            list.get(i).getPositives(), list.get(i).getNegatives(), list.get(i).getHospitalized(),
                            list.get(i).getCurrentOnVent(), list.get(i).getCumulativeOnVent(), list.get(i).getRecovered(),
                            list.get(i).getDataQualityGrade(), list.get(i).getDeaths(), i);
                    result.add(newUpdate);
                }
            }
        }
        if (instances == 0) {
            System.out.println("There are no records for " + originalInput);
        }
        
        else {
            System.out.println(instances + " records are printed out for the state of "
                    + originalInput);
            System.out.println("date    positive    negative    hospitalized    onVentilatorCurrrently  onVentilatorCumulative  recovered   dataQualityGrade    death");
            for (int i = 0; i < instances; i++) {
                String date = String.valueOf(result.get(i).getMonth()) + "/" + String.valueOf(result.get(i).getDay()) + "/" + String.valueOf(result.get(i).getYear());
                System.out.println(date);
            }
        }
    }
    
    public Searcher(ArrayList<CovidUpdate> list, String date) {
        String [] splitDate = date.split("/");
        String inputDay = splitDate[0];
        String inputMonth = splitDate[1];
        String inputYear = splitDate[2];
        
        int day = Integer.parseInt(inputDay);
        int month = Integer.parseInt(inputMonth);
        int year = Integer.parseInt(inputYear);
      
        this.list = list;
        int instances = 0;
        result = new ArrayList<CovidUpdate>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getDay() == day) && (list.get(i).getMonth() == month) && 
                (list.get(i).getYear() == year)) {
                instances++;
            }
        }
        
        for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).getDay() == day) && (list.get(j).getMonth() == month) && 
                (list.get(j).getYear() == year)) {
                CovidUpdate newUpdate = new CovidUpdate(list.get(j).getDate(), list.get(j).getState().name(), 
                    list.get(j).getPositives(), list.get(j).getNegatives(), list.get(j).getHospitalized(),
                    list.get(j).getCurrentOnVent(), list.get(j).getCumulativeOnVent(), list.get(j).getRecovered(),
                    list.get(j).getDataQualityGrade(), list.get(j).getDeaths(), j);
                result.add(newUpdate);
            }
        }
        
        if (instances == 0) {
            System.out.println("There are no records for " + date);
            
        }

        else {
            System.out.println(instances + "records are printed out for " + date);
            System.out.println("date    positive    negative    hospitalized    onVentilatorCurrrently  onVentilatorCumulative  recovered   dataQualityGrade    death");
            for (int i = 0; i < instances; i++) {
                String resultDate = String.valueOf(result.get(i).getMonth()) + "/" + String.valueOf(result.get(i).getDay()) + "/" + String.valueOf(result.get(i).getYear());
                System.out.println(resultDate);
            }
            
        }
    }
    
}
