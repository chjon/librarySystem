/*******************************************************************************
 * File Name:     Date.java
 * Class:         ICS4U-01
 * Description:   This class defines a date.
 *******************************************************************************/

package librarySystem.items;

public class Date {
	private static final int MONTH_FORMAT_LENGTH = 2;			//Amount of digits in a month
	private static final int DAY_FORMAT_LENGTH = 2;				//Amount of digits in a day
	private static final int DAYS_IN_YEAR = 365;				//Number of days in a year
	private static final int DAYS_IN_MONTH = 30;				//Number of days in a month
	private static final char PAD_CHAR = '0';				//Filler character for dates
	
	private int year;       //Year
	private int month;      //Month
	private int day;        //Day
	private Calendar cal;   //Calendar

	public Date (int year, int month, int day, Calendar cal) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.cal = cal;
	} //Date constructor
	
	public int getYear () {
		return year;
	} //year accessor
	
	public int getMonth () {
		return month;
	} //month accessor
	
	public int getDay () {
		return day;
	} //day accessor
	
	//compares two date objects and returns the days in between
	//is negative if the compared date is later
	public int compareTo (Date other) {
		int dayDifference = 0;
		
		dayDifference += (year - other.year) * cal.getDaysInYear();
		dayDifference += cal.getDaysByMonth(month) - cal.getDaysByMonth(other.month);
		dayDifference += day - other.day;
		
		return dayDifference;
	} //compareDay method
	
	//compares two date objects and returns the days in between
	//is negative if the compared date is later
	public int compareTo (Date other, int daysFromCurrent, int daysFromOther) {
		return compareTo(other) - daysFromCurrent + daysFromOther;
	} //compareDay method
	
	//Pad months and days with zeros
	private static String pad (int num, int length) {
		String output = "" + num;
		
		while (output.length() < length) {
			output = PAD_CHAR + output;
		} //while loop
		
		return output;
	} //pad method
	
	public String toString () {
		String output = year + "/";
		output += pad(month, MONTH_FORMAT_LENGTH) + "/";
		output += pad(day, DAY_FORMAT_LENGTH) + "/";
		
		return output;
	} //toString method
} //Date class
