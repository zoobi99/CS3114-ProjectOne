import java.util.ArrayList;

public class Search {

    private ArrayList<CovidUpdate> searchState = new ArrayList<CovidUpdate>();


    public Search(
        ArrayList<CovidUpdate> list,
        String stateName,
        int numRecords) {

        // TO DO: ensure that state name is valid
        // print out statements from the project description

        CovidUpdate state = new CovidUpdate(0, stateName, 0, 0, 0, 0, 0, 0,
            "N/A", 0, 0);

        state.checkState(stateName, true);

        list = CovidParser.getList();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getState() == State.valueOf(stateName)) {
                searchState.add(list.get(i));
            }
        }

        for (int j = 0; j <= numRecords; j++) {
            System.out.println(searchState.get(j));
        }

    }


    public Search(
        CovidUpdate[] list,
        String stateName,
        int numRecords,
        int date) {

    }

}
