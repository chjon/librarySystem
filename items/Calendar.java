/*******************************************************************************
 * File Name:     Calendar.java
 * Class:         ICS4U-01
 * Description:   This class defines a calendar.
 *******************************************************************************/

package librarySystem.items;

import java.io.*;
import librarySystem.core.*;

public class Calendar {
	private static final String MONTH_DAYS_REF_FILE = "Days at start of months";		//file used for calendar
	private static final int NUM_MONTHS = 12;						//number of months in a year
	private static final int DAYS_IN_YEAR = 365;						//number of days in a year
	
	private int[] daysByMonth;
	
	//Calendar constructor
	public Calendar () {
		daysByMonth = new int[NUM_MONTHS];
		
		try { 
			BufferedReader in = new BufferedReader(new FileReader(Library.DATA_FILE_DIRECTORY + "/" + MONTH_DAYS_REF_FILE + ".txt"));
			String input;
			
			for (int i = 0; i < NUM_MONTHS; i++) {
				daysByMonth[i] = Integer.parseInt(in.readLine());
			} //for loop
			
			in.close();
		} catch (Exception e) {
			System.err.println("There was a problem with the file:\t" + e.getMessage());
		} //try-catch structure
	} //Calendar constructor
	
	public int getDaysInYear () {
		return DAYS_IN_YEAR;
	} //getDaysInYear method
	
	public int getDaysByMonth (int month) {
		if (month < 1) {
			return daysByMonth[0];
		} else if (month > NUM_MONTHS) {
			return daysByMonth[NUM_MONTHS - 1];
		} //if structure
		
		return daysByMonth[month - 1];
	} //getDaysByMonth method
} //Calendar class
