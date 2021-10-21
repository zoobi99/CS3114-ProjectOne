import student.TestCase;
//Tests the different methods in CovidUpdate
public class CovidUpdateTest extends TestCase {

	private CovidUpdate update1;
	
	/**
	 * This is a setup method for the test class.
	 */
	public void setUp() {
		update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
				"A+", 405, 0);
	}
	
	/**
	 * This method tests the getter methods
	 * in CovidUpdate.
	 */
	public void testGetters() {
		assertEquals(update1.getDate(), 20200818);
		assertEquals(update1.getDataQualityGrade(), "A+");
		assertEquals(update1.getDataQualityScore(), 13);
		assertEquals(update1.getDay(), 18);
		assertEquals(update1.getMonth(), 8);
		assertEquals(update1.getYear(), 2020);
		assertEquals(update1.getState(), State.valueOf("KS"));
		assertEquals(update1.getPositives(), 35167);
		assertEquals(update1.getNegatives(), 319095);
		assertEquals(update1.getCurrentOnVent(), 24);
		assertEquals(update1.getCumulativeOnVent(), 2024);
		assertEquals(update1.getRecovered(), 1409);
		assertEquals(update1.getDeaths(), 405);
		assertEquals(update1.getIndex(), 0);
	}
	
	/**
	 * This method tests the getState method with an invalid
	 * state name.
	 */
	public void testGetState() {
		CovidUpdate update2 = new CovidUpdate(20200818, "KD", 35167, 319095, 2034, 24, 200, 1409,
				"A+", 405, 0);
		assertNull(update2.getState());
		
		
	}
	
	/**
	 * This method tests the method checkState with
	 * a valid and invalid state name.
	 */
	public void testCheckState() {
	    assertEquals(update1.checkState("KS", true), true);
	    update1 = new CovidUpdate(20200818, "ZZ", 35167, 319095, 2024, 24, 2024, 1409,
            "A+", 405, 0);
	    assertEquals(update1.checkState("ZZ", true), false);
	}
	
	/**
	 * This method tests all the cases in the
	 * method getGradeScore.
	 */
	public void testGetGradeScore() {
	    update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "A", 405, 0);
	    assertEquals(update1.getDataQualityScore(), 12);
	    update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "A-", 405, 0);
        assertEquals(update1.getDataQualityScore(), 11);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "B+", 405, 0);
        assertEquals(update1.getDataQualityScore(), 10);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "B", 405, 0);
        assertEquals(update1.getDataQualityScore(), 9);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "B-", 405, 0);
        assertEquals(update1.getDataQualityScore(), 8);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "C+", 405, 0);
        assertEquals(update1.getDataQualityScore(), 7);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "C", 405, 0);
        assertEquals(update1.getDataQualityScore(), 6);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "C-", 405, 0);
        assertEquals(update1.getDataQualityScore(), 5);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "D+", 405, 0);
        assertEquals(update1.getDataQualityScore(), 4);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "D", 405, 0);
        assertEquals(update1.getDataQualityScore(), 3);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "D-", 405, 0);
        assertEquals(update1.getDataQualityScore(), 2);
        update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2024, 24, 2024, 1409,
            "F", 405, 0);
        assertEquals(update1.getDataQualityScore(), 1);
        update1 = new CovidUpdate(20200818, "KS", 32167, 319095, 2024, 24, 2024, 1409,
            "Z", 405, 0);
        assertEquals(update1.getDataQualityScore(), 0);
	  
	}
	
	
}
