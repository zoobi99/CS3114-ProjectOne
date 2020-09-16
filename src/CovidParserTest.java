import java.io.IOException;

import student.TestCase;

public class CovidParserTest extends TestCase {

	private CovidParser parser;
	private CovidUpdate[] covidList;
	
	public void setUp() throws IOException {
		parser = new CovidParser("head_100_random_30.csv");
		covidList = parser.getList();
	}
	
	public void testGetList() {
		assertEquals(covidList[0].getDay(), 18);
		assertEquals(covidList[1].getDeaths(), 405);
		
	}
	
	/**
	 * Tests the case where there is a missing line
	 * in the csv file.
	 */
	public void testMissingLineCase() {
	    //The missing line is in between these two indexes.
	    //The parser handles this and skips to the next entry.
	    assertEquals(covidList[14].getDataQualityGrade(), "B");
	    assertEquals(covidList[15].getDataQualityGrade(), "A+");
	}
}
