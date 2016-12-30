/*******************************************************************************
 * File Name:     Date.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/29
 * Description:   This class defines a date.
 *******************************************************************************/

public class Date {
	private static final int MONTH_FORMAT_LENGTH = 2;
	private static final int DAY_FORMAT_LENGTH = 2;
	private static final int DAYS_IN_YEAR = 365;
	private static final int DAYS_IN_MONTH = 30;
	private static final char PAD_CHAR = '0';
	
	private int year;			//Year
	private int month;		//Month
	private int day;			//Day
	private Calendar cal;	//Calendar

	public Date (int year, int month, int day, Calendar cal) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.cal = cal;
	} //Date constructor
	
	public int getYear () {
		return year;
	} //getYear method
	
	public int getMonth () {
		return month;
	} //getYear method
	
	public int getDay () {
		return day;
	} //getYear method
	
	public int compareTo (Date other) {
		int dayDifference = 0;
		
		dayDifference += (year - other.year) * cal.getDaysInYear();
		dayDifference += cal.getDaysByMonth(month) - cal.getDaysByMonth(other.month);
		dayDifference += day - other.day;
		
		return dayDifference;
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