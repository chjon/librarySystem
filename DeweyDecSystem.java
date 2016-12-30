/*******************************************************************************
 * File Name:     DeweyDecSystem.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/12/24
 * Description:   This class defines the Dewey Decimal system.
 *******************************************************************************/

import java.io.*;

public class DeweyDecSystem {
	private static final String DEWEY_DEC_REF_FILE = "Dewey Decimal Classes";
	private static final int DEWEY_DEC_GENRE_CODE_LENGTH = 3;
	private static final int MAX_DEWEY_DEC_NUM = 1000;
	
	private int curMaxDeweyDecNum;
	private String[] deweyDecClasses = null;
	private int[] deweyDecNums = null;
	
	//DeweyDecSystem constructor
	public DeweyDecSystem () {
		deweyDecClasses = new String[MAX_DEWEY_DEC_NUM];
		deweyDecNums = new int[MAX_DEWEY_DEC_NUM];
		curMaxDeweyDecNum = 0;
		
		//Load Dewey Decimal classes from file - File input
		try {
			BufferedReader in = new BufferedReader(new FileReader(DEWEY_DEC_REF_FILE + ".txt"));
			String input;
			
			while ((input = in.readLine()) != null) {
				int i, inputDeweyDecNum = Integer.parseInt(input.substring(
					0, DEWEY_DEC_GENRE_CODE_LENGTH));
				input = input.substring(DEWEY_DEC_GENRE_CODE_LENGTH + 1);
			
				//Insert classes into the array - quasi-insertion sort
				for (i = curMaxDeweyDecNum; i > 0 &&
					i < deweyDecNums.length &&
					inputDeweyDecNum < deweyDecNums[i - 1]; i--) {
					
					deweyDecClasses[i] = deweyDecClasses[i - 1];
					deweyDecNums[i] = deweyDecNums[i - 1];
				} //for loop
				
				deweyDecClasses[i] = input;
				deweyDecNums[i] = inputDeweyDecNum;
				curMaxDeweyDecNum++;
			} //while loop
			
			in.close();
		} catch (Exception e) {
			System.err.println("There was a problem with the file:\t" + e.getMessage());
		} //try-catch structure
	} //DeweyDecSystem constructor
	
	//getGenre method
	public String getGenre (double deweyDecNum) {
		int lowerBound = 0, upperBound = curMaxDeweyDecNum, middle;
		
		//Binary search for genre
		while (lowerBound <= upperBound) {
			middle = (lowerBound + upperBound) / 2;
			
			if (deweyDecNums[middle] == (int)deweyDecNum) {
				return deweyDecClasses[middle];
			} else if (deweyDecNums[middle] > (int)deweyDecNum) {
				upperBound = middle - 1;
			} else {
				lowerBound = middle + 1;
			} //if structure
		} //while loop
		
		return "";
	} //getGenre method
} //DeweyDecSystem class