package mainpkg;

import java.util.*;
import java.io.*;
import javax.swing.*;

public class AlgorithmOne {

	/* This method accepts input from user for points array */
	public static void readInput(int rowCount, int dimensionCount, List<List<Float>> pointsArray) {
		pointsArray.clear();
		JOptionPane.showMessageDialog(null,
				"Enter values for " + dimensionCount + " dimension for " + rowCount + " points");
		for (int i = 0; i < rowCount; i++) {
			List<Float> tempLine = new ArrayList<Float>();
			for (int j = 0; j < dimensionCount; j++) {
				tempLine.add(Float.parseFloat(JOptionPane.showInputDialog(null,
						"Enter (float) value of point " + i + 1 + " and dimension " + j + 1)));
			}
			pointsArray.add(tempLine);
		}
	}

	/* This method reads the input from a given file */
	public static ArrayList<ArrayList<Float>> readInput(String fileName, ArrayList<ArrayList<Float>> pointsArray) {
		pointsArray.clear(); /* Clear the points array */
		int rowsInFile = 0;
		int dimensionsInFile = 0;
		int rowCounter = -999;
		int dimensionCounter = -999;
		try {
			File inputFile = new File(fileName); /* Open the input file */
			Scanner inputScanner = new Scanner(inputFile);

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
			rowCounter = 0;
			while (inputScanner.hasNextLine() & ++rowCounter <= rowsInFile) {
				String line = inputScanner.nextLine(); /* Read each line from file */
				Scanner lineBreaker = new Scanner(line); /* Break up each line into parts */
				dimensionCounter = 0;
				ArrayList<Float> tempLine = new ArrayList<Float>(); /* Temp list of float for each row of file */
				/* Iterating each token (float value) from the line */
				while (lineBreaker.hasNext() & ++dimensionCounter <= dimensionsInFile) {
					tempLine.add(lineBreaker.nextFloat()); /* Add each token to temp list */
				}
				pointsArray.add(tempLine);	/*Add row to points array */
				lineBreaker.close();
			}
			System.out.println("\nRowCounter::" + rowCounter);
			System.out.println("Dia Counter::" + dimensionCounter);
			System.out.println("PointsArray size::" + pointsArray.size());
			System.out.println("PointsArray Row 0 size::" + pointsArray.get(0).size());
			inputScanner.close();
			System.out.println("Printing points array:::"); Thread.sleep(3000);
			for (int i = 0; i < rowCounter-1; i++) {
				for (int j = 0; j < dimensionCounter-1; j++) {
					System.out.print(pointsArray.get(i).get(j) + "");
				}
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			/*
			 * System.out.println(e.getLocalizedMessage()); //
			 * JOptionPane.showMessageDialog(null,e.getLocalizedMessage()); System.exit(0);
			 */
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Close the file
		}
		return pointsArray;
	}

	/*
	 * This is the first algorithm to count the number of interesting points. It
	 * return the count of interesting points.
	 */
	public static int countUsingAlgorithmOne(int numOfRows, int numOfDiamensions) {
		int interestingPointsCount = 0;

		return interestingPointsCount;
	}

	public static int SimpleAlgo2(int n, int d, ArrayList<ArrayList<Float>> pointsArray) {
		List<Boolean> isBest = new ArrayList<Boolean>(Collections.nCopies(n+10, true));
		System.out.print( n + " is the value of n");
		int anotherCounter=0;
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
					if (worse_somewhere && not_better) {
						isBest.set(i, false); anotherCounter++; }
				}
			}
		}
		System.out.println("Another counter = "+ anotherCounter);
		int count = 0;
		for (int i = 0; i < n; i++)
			if (isBest.get(i))
				count++;
		
		for (int i=0; i<n; i++)
	        if (isBest.get(i)) {
	            for (int k=0; k<d; k++)


	            System.out.println();
	        }
		
		return count;
	}

	/* Main method */
	public static void main(String[] args) throws InterruptedException {
		ArrayList<ArrayList<Float>> carPointsArray = new ArrayList<ArrayList<Float>>(); // This holds the dimensional
																						// array
//		readInput(5,2); /* This can be used for small input set */
		String filePath = System.getProperty("user.dir") + "\\src\\mainpkg\\" + "3.in"; /* Specify your file name here */
		carPointsArray = readInput(filePath, carPointsArray);
		System.out.println("PointsArray size::" + carPointsArray.size());
		System.out.println("PointsArray Row 0 size::" + carPointsArray.get(0).size()); Thread.sleep(3000);
		long start = System.nanoTime(); /* Record starting time */

//		  System.out.println("Number of interesting points using Algorithm One : " +
//		  countUsingAlgorithmOne(5,2,List<List<Float>> pointsArray)); /* Calling  algorithmOne */

		System.out.println("Number of interesting points using SimpleAlgo2 : "
				+ SimpleAlgo2(5, 2, carPointsArray)); /* Calling SimpleAlgo2 */
		long end = System.nanoTime(); /* Record ending time */
		System.out.println("Time taken by SimpleAlgo2 is " + (end-start) + " nanoseconds!");

		// JOptionPane.showMessageDialog(null,"Time taken by Algorithm one is " +
		// timeTaken + " nanoseconds!");

	}

}
