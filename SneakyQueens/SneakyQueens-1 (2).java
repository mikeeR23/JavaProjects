// Michael Ramirez
// Fall 2017
// COP 3503
// SneakyQueens Program

// This program takes an arbitrary board size and amount of queens and determines if 
// any of those queens attack each other or not

import java.io.*;
import java.util.*;

public class SneakyQueens
{	
	public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		boolean [] rowArray = new boolean[boardSize+1];
		boolean [] colArray = new boolean[boardSize+1];
		boolean [] posArray = new boolean[boardSize*2+1];
		boolean [] negArray = new boolean[boardSize*2+1];
		boolean sign;

		// Loops through string to get int values for x and y 
		for(int i = 0;i < coordinateStrings.size();i++)
		{
			// Replaces all letters with empty string
			int col = converts(coordinateStrings.get(i).replaceAll("[^a-z]", ""));
			// Replace all numbers with emptry string
			int row = Integer.parseInt(coordinateStrings.get(i).replaceAll("[^0-9]", ""));

			int slope = 0;
			int slope1 = 0;
			sign = true;
		
		// Get slopes for diagonally placed queens	
		for(int k = 0;k < 2;k++)
		{
			if(sign)
			{
				if(col == row)
					slope = col - row + boardSize;
				else if(col > row)
					slope = col - row + boardSize;
				else
					slope = row - col;
			}
			else
				slope1 = col + row;
			sign = false;
		}

		// Check if queen is attacking another by checking spot for true value
		if(colArray[col] || rowArray[row] || posArray[slope] || negArray[slope1])
			return false;
		else
		{
			// Set the values passed from string to true 
			rowArray[row] = true;
			colArray[col] = true;
			posArray[slope] = true;
			negArray[slope1] = true;
		}	
		}
		return true;
	}

	// Take each letter in string get ascii value and subtract 96
	public static int converts(String convertString)
	{
		int i = 0;
		int total;

		total = convertString.charAt(i) - 96;

		for(i = 1;i < convertString.length();i++)
			total = ((convertString.charAt(i) - 96) + total*26);
		
		return total;
	}

	//Difficulty of assignment
	public static double difficultyRating()
	{
		return 4; 
	}

	//Total hours spent on program
	public static double hoursSpent()
	{
		return 20;
	}
}