package mainpkg;

import java.util.*;
import java.io.*;
import javax.swing.*;

public class AlgorithmOne {

	/* This method accepts input from user for points array */
	public static void readInput(int rowCount, int dimensionCount,List<List<Float>> pointsArray) {
		pointsArray.clear();
		JOptionPane.showMessageDialog(null,
				"Enter values for " + dimensionCount + " dimension for " + rowCount + " points");
		for (int i = 0; i < rowCount; i++) {
			List<Float> tempLine = new ArrayList<Float>();
			for (int j = 0; j < dimensionCount; j++) {
				tempLine.add(Float.parseFloat(JOptionPane.showInputDialog(null,
						"Enter (float) value of point " + i+1 + " and dimension " + j+1)));
			}
			pointsArray.add(tempLine);
		}
	}

	/* This method reads the input from a given file */
	public static void readInput(String fileName,List<List<Float>> pointsArray) {
		int rowsInFile = 0;
		int dimensionsInFile = 0;
		try {
			pointsArray.clear(); /* Clear the points array */
			File inputFile = new File(fileName); /* Open the input file */
			Scanner inputScanner = new Scanner(inputFile);

			List<Float> tempLine = new ArrayList<Float>(); /* Temp list of float for each row of file */

			/* Read the first line of the file to get value of n and d */
			try {
				rowsInFile = inputScanner.nextInt();
				dimensionsInFile = inputScanner.nextInt();
				inputScanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("First line of file is invalid. It must be integer values of n and d.");
			}

			/*
			 * Read rest of the lines of the File Each line is read, then split into float
			 * values and stored in temp list This list is then added to points array
			 */
			int rowCounter = 1;
			while (inputScanner.hasNextLine() && rowCounter <= rowsInFile) {
				String line;////////////////////////////////////////////////////////////////////////////////////
				System.out.println(line = inputScanner.nextLine()); /* Read each line from file */
				Scanner lineBreaker = new Scanner(line); /* Break up each line into parts */
				int dimensionCounter = 1;
				while (lineBreaker.hasNext()
						&& dimensionCounter <= dimensionsInFile) { /* Iterating each token (float value) from the line */
					tempLine.add(lineBreaker.nextFloat()); /* Add each token to temp list */
					dimensionCounter++; /* Increment the counter */
				}
				System.out.println(tempLine);
				lineBreaker.close();
				rowCounter++; /* Increment the rowCounter */
				pointsArray.add(tempLine);
				tempLine.clear();
			}
			System.out.println(pointsArray);
			inputScanner.close();

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			//JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
			System.exit(0);
		} finally {
			// Close the file
			System.out.println(pointsArray+"LOL");
			for (int i = 0; i < rowsInFile; i++) {
				for (int j = 0; j < dimensionsInFile; j++) 
						System.out.print(pointsArray.get(i).get(j) + " ");
				System.out.println();
			}
		}
	}

	/*
	 * This is the first algorithm to count the number of interesting points. It
	 * return the count of interesting points.
	 */
	public static int countUsingAlgorithmOne(int numOfRows, int numOfDiamensions) {
		int interestingPointsCount = 0;

		return interestingPointsCount;
	}

	public static int SimpleAlgo2(int n, int d,List<List<Float>> pointsArray) {
		List<Boolean> isBest = new ArrayList<Boolean>(Collections.nCopies(n, true));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isBest.get(i)) {
					boolean worse_somewhere = false;
					for (int k = 0; k < d; k++)
						if (pointsArray.get(i).get(k) < pointsArray.get(j).get(k))
							worse_somewhere = true;

					boolean not_better = true;
					for (int k = 0; k < d; k++)
						if (pointsArray.get(i).get(k) > pointsArray.get(j).get(k))
							not_better = false;
					if (worse_somewhere && not_better)
						isBest.set(i, false);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < n; i++)
			if (isBest.get(i))
				count++;
		return count;
	}
	
	
	/* Main method */
	public static void main(String[] args) {
		List<List<Float>> pointsArray = new ArrayList<List<Float>>(); // This holds the dimensional array
//		readInput(5,2); /* This can be used for small input set */
		String filePath = System.getProperty("user.dir") + "\\src\\mainpkg\\" + "1.in"; /* Specify your file name here */
		readInput(filePath, pointsArray);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) 
					System.out.print(pointsArray.get(i).get(j) + " ");
			System.out.println();
		}
		
		long start=System.nanoTime();	/* Record starting time */
		/*System.out.println("Number of interesting points using Algorithm One : " 
				+ countUsingAlgorithmOne(5,2,List<List<Float>> pointsArray));	/* Calling algorithmOne */
		
		System.out.println("Number of interesting points using SimpleAlgo2 : " 
				+ SimpleAlgo2(5, 2, pointsArray));	/* Calling SimpleAlgo2 */
		long end = System.nanoTime();	/* Record ending time	*/
		long timeTaken = end - start;
		System.out.println("Time taken by Algorithm one is " + timeTaken + " nanoseconds!");
		//JOptionPane.showMessageDialog(null,"Time taken by Algorithm one is " + timeTaken + " nanoseconds!");
		
	}

}
