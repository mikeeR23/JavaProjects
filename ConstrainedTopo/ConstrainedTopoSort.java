// Michael Ramirez
// COP 3503 - Fall 2017
// Assignment 5 - ConstrainedTopoSort

// This programs determines whether the directed graph has valid topological sorts
// where x precedes y, x and y being vertices

import java.io.*;
import java.util.*;

public class ConstrainedTopoSort
{
	private int matrix[][];
	private int N, M;
	private int [] incoming;
	private Queue<Integer> q;
	private Queue<Integer> list;

	// Constructor opens file and reads graph into adjacency matrix
	public ConstrainedTopoSort(String filename) throws IOException
	{
		Scanner in = new Scanner(new File(filename));
		
		// Capture first val which is how many vertices there are
		N = in.nextInt();
	
		// Create NxN matrix
		matrix = new int[N][N];
 
 		// Read in values into 2D array, fill in unconnected vertices with -1
 		for(int i = 0;i < N;i++)
 		{
 			// Get first value of each line 
 			M = in.nextInt();

 			for(int j = 0;j < M;j++)
 			{
 				matrix[i][j] = in.nextInt();
 			}
 
			for(int j = M;j < N;j++)
			{
				matrix[i][j] = -1;
			}
		}
	}

	// Determines whether the graph has valid topological sort where x precedes y
	public boolean hasConstrainedTopoSort(int x, int y) throws IOException
	{
		// Get incoming edge amount for each node
		countincomingEdges();

		// Check for valid topo sort
		if(!checkTopo())
			return false;

		countincomingEdges();

		// Both have 0 incoming edges
		if(incoming[x] == 0 && incoming[y] == 0)
			return true;

		// x has 0 incoming edges but y does not
		else if(incoming[x] == 0 && incoming[y] != 0)
			return true;
		
		// check for x precedes y
		if(checkXbeforeY(x, y))
			return true;
		
		return false;
	}

	// Returns true if x comes before y, false otherwise
	public boolean checkXbeforeY(int x, int y)
	{
		list = new LinkedList<Integer>();
		boolean [] visited = new boolean[N+1];

		countincomingEdges();

		for(int i = 1;i <= N;i++)
		{
			if(incoming[i] == 0)
			{
				list.add(i);
				visited[i] = true;
			}
		}

		while(!list.isEmpty())
		{
			if(list.contains(x) && list.contains(y) && visited[x] && visited[y])
			{
				return true;
			}

			else if(visited[x] && !visited[y])
			{
				return true;
			}

			int node = list.remove();

			for(int i = 0;i < N;i++)
			{
				if(matrix[node-1][i] != -1)
				{
					if(--incoming[matrix[node-1][i]] == 0)
					{
						list.add(matrix[node-1][i]);
						visited[matrix[node-1][i]] = true;
					}
				}
			}

		}

		return false;
	}

	// Returns true for valid topological sort, false otherwise
	public boolean checkTopo()
	{
		q = new LinkedList<Integer>();

		// Add any vertices with 0 incoming edges 
		for(int i = 1;i <= N;i++)
			if(incoming[i] == 0)
				q.add(i);

		// Graph that has no vertices with 0 incoming edges
		if(q.isEmpty())
			return false;

		// Run topological sort 
		// Szumlanski's outer while loop from toposort.java
		/*
			while(!q.isEmpty())
			{
				int node = q.remove();
				....
			}
		*/
		while(!q.isEmpty())
		{
			int node = q.remove();

			for(int i = 0;i < N;i++)
			{
				if(matrix[node-1][i] != -1)
				{
					// Decrement amount of incoming edges
					if(--incoming[matrix[node-1][i]] == 0)
					{
						q.add(matrix[node-1][i]);
					}
				}
			}	
		}

		// Check if all nodes have a value of 0, if not then we have invalid topo sort
		for(int i = 1;i < N;i++)
			if(incoming[i] != 0)
				return false;
	
		q.clear();
			
		return true;
	}

	// Count how many incoming edges each node has
	public void countincomingEdges()
	{
		incoming = new int[N+1];

		for(int i = 0;i < N;i++)
			for(int j = 0;j < N;j++)
				if(matrix[i][j] != -1)
					incoming[matrix[i][j]]++;
		
	}

	public static double difficultyRating()
	{
		return 4;
	}

	public static double hoursSpent()
	{
		return 20;
	}
}