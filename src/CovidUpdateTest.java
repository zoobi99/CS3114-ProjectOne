import student.TestCase;

public class CovidUpdateTest extends TestCase {

	private CovidUpdate update1;
	
	/**
	 * This is a setup method for the test class.
	 */
	public void setUp() {
		update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2034, 24, 200, 1409,
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
	
	
}
