import student.TestCase;

public class CovidUpdateTest extends TestCase {

	private CovidUpdate update1;
	
	public void setUp() {
		update1 = new CovidUpdate(20200818, "KS", 35167, 319095, 2034, 24, 200, 1409,
				"A+", 405, 0);
	}
	
	public void testGetters() {
		assertEquals(update1.getDate(), 20200818);
		assertEquals(update1.getDataQualityScore(), 13);
		assertEquals(update1.getDay(), 18);
		assertEquals(update1.getMonth(), 8);
		assertEquals(update1.getYear(), 2020);
		assertEquals(update1.getState(), State.valueOf("KS"));
	}
	
	public void testGetState() {
		CovidUpdate update2 = new CovidUpdate(20200818, "KD", 35167, 319095, 2034, 24, 200, 1409,
				"A+", 405, 0);
		assertNull(update2.getState());
	}
}
