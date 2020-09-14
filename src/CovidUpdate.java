
public class CovidUpdate {

	private int day;
	private int month;
	private int year;
	private int date;
	private State state;
	private int positiveCases;
	private int negativeCases;
	private int hospitalized;
	private int onVentilatorCurrently;
	private int onVentilatorCumulative;
	private int recovered;
	private int gradeScore;
	private int deaths;
	
	/**
	 * Stores one row of data, param tags need to be fixed
	 * 
	 * @param date
	 * @param state
	 * @param positive
	 * @param negative
	 * @param hospitalized
	 * @param currVent
	 * @param cumVent
	 * @param recovered
	 * @param grade
	 * @param deaths
	 */
	public CovidUpdate(int date, String state, int positive, int negative, 
			int hospitalized, int currVent, int cumVent, int recovered, 
			String grade, int deaths) {
		this.date = date;
		this.checkState(state);
		this.positiveCases = positive;
		this.negativeCases = negative;
		this.hospitalized = hospitalized;
		this.onVentilatorCurrently = currVent;
		this.onVentilatorCumulative = cumVent;
		this.recovered = recovered;
		this.gradeScore = this.getGradeScore(grade);
		this.deaths = deaths;
		int tempDate = date;
		this.day = date % 100;
		tempDate /= 100;
		this.month = tempDate % 100;
		tempDate /= 100;
		this.year = tempDate;
	}
	
	private int getGradeScore(String grade) {
		if (grade.equals("A+")) {
			return 13;
		}
		else if (grade.equals("A")) {
			return 12;
		}
		else if (grade.equals("A-")) {
			return 11;
		}
		else if (grade.equals("B+")) {
			return 10;
		}
		else if (grade.equals("B")) {
			return 9;
		}
		else if (grade.equals("B-")) {
			return 8;
		}
		else if (grade.equals("C+")) {
			return 7;
		}
		else if (grade.equals("C")) {
			return 6;
		}
		else if (grade.equals("C-")) {
			return 5;
		}
		else if (grade.equals("D+")) {
			return 4;
		}
		else if (grade.equals("D")) {
			return 3;
		}
		else if (grade.equals("D-")) {
			return 2;
		}
		else if (grade.equals("F")) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	private void checkState(String stateName) {
		try {
			this.state = State.valueOf(stateName);
		}
		catch (Exception e) {
			System.out.println(stateName +  " is not a recognized state abreviation");
		}
	}
	public int getDataQualityScore() {
		return this.gradeScore;
	}
	
	public int getDate() {
		return this.date;
	}
	
	public State getState() {
		return this.state;
	}
	
	public int getPositives() {
		return this.positiveCases;
	}
	
	public int getNegatives() {
		return this.negativeCases;
	}
	
	public int getHospitalized() {
		return this.hospitalized;
	}
	
	public int getCurrentOnVent() {
		return this.onVentilatorCurrently;
	}
	
	public int getCumulativeOnVent() {
		return this.onVentilatorCumulative;
	}
	
	public int getRecovered() {
		return this.recovered;
	}
	
	public int getDeaths() {
		return this.deaths;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
}
