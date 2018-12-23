// Michael Ramirez
// NID: mi705719
// COP 3503 - Fall 2017
// Assignment 7 

// This program uses a dynamic programming solution with O(n) runtime
// to get the maximum knowledge of an array where no two consecutive 
// numbers can be chosen

import java.io.*;
import java.util.*;

public class RunLikeHell
{
	public static int maxGain(int [] block)
	{

		if(block.length == 0)
			return 0;
		if(block.length == 1)
			return block[0];

		int [] sums = new int[block.length];

		 //sums[0] = block[0];
		 //sums[1] = block[1];

		sums[block.length-1] = block[0];
		sums[block.length-2] = block[1];
		
				// Get the total for each index
		for(int i = 2;i < block.length;i++)
		{
			if(i == 2)
				sums[i] = block[i-2] + block[i];
			else
				sums[i] = Math.max(sums[i-2], sums[i-3]) + block[i];
		}

		// return the max value between the last sum and second to last sum
		return Math.max(sums[block.length-1], sums[block.length-2]);
	}

	public static double difficultyRating()
	{
		return 2;
	}

	public static double hoursSpent()
	{
		return 5;
	}
}