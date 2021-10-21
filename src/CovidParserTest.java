import java.io.IOException;
import java.util.ArrayList;

import student.TestCase;
//test class for CovidParser
public class CovidParserTest extends TestCase {

    private CovidParser parser;
    private ArrayList<CovidUpdate> covidList;
    
    public void setUp() throws IOException {
        covidList = null;
        parser = new CovidParser("head_100_random_30.csv", covidList);
        covidList = parser.getList();
    }
    
    public void testGetList() {
        assertEquals(covidList.get(0).getDay(), 18);
        assertEquals(covidList.get(1).getDeaths(), 1059);
    }
    
    /**
     * Tests the case where there is a missing line
     * in the csv file.
     */
    public void testMissingLineCase() {
        //The missing line is in between these two indexes.
        //The parser handles this and skips to the next entry.
        assertEquals(covidList.get(14).getDataQualityGrade(), "A+");
        assertEquals(covidList.get(15).getDataQualityGrade(), "A");
    }
}
