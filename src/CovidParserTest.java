import java.io.IOException;

import student.TestCase;

public class CovidParserTest extends TestCase {

	private CovidParser parser;
	
	public void setUp() throws IOException {
		parser = new CovidParser("head_100_random_30.csv");
	}
	
	public void testGetList() {
		CovidUpdate[] covidList = parser.getList();
		assertEquals(covidList[0].getDay(), 18);
	}
}
