
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
    private int index;
    private String grade;


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
    public CovidUpdate(
        int date,
        String state,
        int positive,
        int negative,
        int hospitalized,
        int currVent,
        int cumVent,
        int recovered,
        String grade,
        int deaths,
        int index) {
        this.date = date;
        this.checkState(state, true);
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
        this.grade = grade;
    }

    /**
     * This method returns a integer value
     * based on the input grade.
     * @param grade The input grade 
     * @return an integer value
     */
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

    /**
     * This method checks to see if the given state
     * name is valid.
     * @param stateName The state that is given as input
     * @param isConstructor boolean check
     * @return true if it is a state, false if not
     */
    public boolean checkState(String stateName, boolean isConstructor) {
        try {
            this.state = State.valueOf(stateName);
            return true;
        }
        catch (Exception e) {
            if (isConstructor) {
                System.out.println("State of " + stateName
                    + " does not exist!");
            }
            return false;
        }
    }

    /**
     * This is a getter method that returns
     * a value for the data quality score.
     * @return grade score
     */
    public int getDataQualityScore() {
        return this.gradeScore;
    }

    /**
     * This is a getter method that returns
     * the date value.
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * This is a getter method that returns
     * the state value.
     * @return the state
     */
    public State getState() {
        return this.state;
    }

    /**
     * This is a getter method that returns
     * the number of positive cases.
     * @return number of positive cases
     */
    public int getPositives() {
        return this.positiveCases;
    }

    /**
     * This is a getter method that returns
     * the number of negative cases.
     * @return number of negative cases
     */
    public int getNegatives() {
        return this.negativeCases;
    }

    /**
     * This is a getter method that returns
     * the number of people hospitalized.
     * @return number of people hospitalized
     */
    public int getHospitalized() {
        return this.hospitalized;
    }

    /**
     * This is a getter method that returns
     * the number of people on ventilator.
     * @return num of people on ventilator
     */
    public int getCurrentOnVent() {
        return this.onVentilatorCurrently;
    }

    /**
     * This is a getter method that returns 
     * the cumulative number on ventilators.
     * @return cumulative number on ventilators.
     */
    public int getCumulativeOnVent() {
        return this.onVentilatorCumulative;
    }

    /**
     * This is a getter method that returns 
     * the number of people recovered.
     * @return number of people recovered
     */
    public int getRecovered() {
        return this.recovered;
    }

    /**
     * This is a getter method that returns
     * the number of deaths.
     * @return number of deaths
     */
    public int getDeaths() {
        return this.deaths;
    }

    /**
     * This is a getter method that returns
     * the day value.
     * @return the day value
     */
    public int getDay() {
        return this.day;
    }

    /**
     * This is a getter method that returns
     * the month value.
     * @return the month value
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * This is a getter method that returns
     * the year value.
     * @return the year value
     */
    public int getYear() {
        return this.year;
    }

    /**
     * This is a getter method that returns
     * the value of the index.
     * @return value of the index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * This is a getter method that returns 
     * the data quality grade.
     * @return the data quality grade.
     */
    public String getDataQualityGrade() {
        return this.grade;
    }
}
