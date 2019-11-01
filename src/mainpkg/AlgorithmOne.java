package mainpkg;

import java.util.*;
import java.io.*;
import javax.swing.*;

public class AlgorithmOne {
	
	static int rows = 0, dimensions = 0;
	static ArrayList<ArrayList<Float>> carPointsArray;
	/* This method accepts input from user for points array */
	public static void readInput(int rowCount, int dimensionCount, List<List<Float>> pointsArray) {
		pointsArray.clear();
		JOptionPane.showMessageDialog(null, "Enter values for " + dimensionCount + " dimension for " + rowCount + " points");
		for (int i = 0; i < rowCount; i++) {
			List<Float> tempLine = new ArrayList<Float>();
			for (int j = 0; j < dimensionCount; j++) {
				tempLine.add(Float.parseFloat(JOptionPane.showInputDialog(null, "Enter (float) value of point " + i + 1 + " and dimension " + j + 1)));
			}
			pointsArray.add(tempLine);
		}
	}

	/* This method reads the input from a given file */
	public static ArrayList<ArrayList<Float>> readInput(String fileName) {
		carPointsArray.clear(); /* Clear the points array */
		int rowCounter = -999;
		int dimensionCounter = -999;
		try {
			File inputFile = new File(fileName); /* Open the input file */
			Scanner inputScanner = new Scanner(inputFile);

			/* Read the first line of the file to get value of n and d */
			try {
				rows = inputScanner.nextInt();
				dimensions = inputScanner.nextInt();
				inputScanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("First line of file is invalid. It must be integer values of n and d.");
			}

			/*
			 * Read rest of the lines of the File Each line is read, then split into float
			 * values and stored in temp list This list is then added to points array
			 */
			rowCounter = 0;
			while (inputScanner.hasNextLine() & ++rowCounter <= rows) {
				String line = inputScanner.nextLine(); /* Read each line from file */
				Scanner lineBreaker = new Scanner(line); /* Break up each line into parts */
				dimensionCounter = 0;
				ArrayList<Float> tempLine = new ArrayList<Float>(); /* Temp list of float for each row of file */
				/* Iterating each token (float value) from the line */
				while (lineBreaker.hasNext() & ++dimensionCounter <= dimensions) {
					tempLine.add(lineBreaker.nextFloat()); /* Add each token to temp list */
				}
				carPointsArray.add(tempLine);	/*Add row to points array */
				lineBreaker.close();
			}
			inputScanner.close();
//			System.out.println("RowCounter::" + rowCounter);
//			System.out.println("Dia Counter::" + dimensionCounter);
//			System.out.println("PointsArray size::" + pointsArray.size());
			/*
			 * System.out.println("Printing points array:::"); Thread.sleep(10000); for (int
			 * i = 0; i < rowCounter-1; i++) { for (int j = 0; j < dimensionCounter-1; j++)
			 * { System.out.print(pointsArray.get(i).get(j) + ""); } System.out.println(); }
			 */
		} catch (FileNotFoundException e) {
			  System.out.println(e.getLocalizedMessage()); //
			  JOptionPane.showMessageDialog(null,e.getLocalizedMessage()); 
			  System.exit(0);
		} finally {
			// Close the file
		}
		return carPointsArray;
	}

	/*
	 * This is the first algorithm to count the number of interesting points. It
	 * return the count of interesting points.
	 */
	public static int countUsingAlgorithmOne(int startIndex, int endIndex, int numOfDiamensions, ArrayList<ArrayList<Float>> pointsArray) {
		int interestingPointsCount = 0;
//		System.out.println("startIndex "+startIndex);
//		System.out.println("endIndex "+endIndex);
		int midIndex = ((endIndex-startIndex)/2)+1;
		if(startIndex>=endIndex) {System.out.println("Killing the thread");System.exit(0);}
		if (endIndex - startIndex <= 2)
			SimpleAlgo3(startIndex, endIndex, numOfDiamensions, pointsArray);
		else if ((endIndex - midIndex) <= 2)
			 SimpleAlgo3(startIndex, endIndex, numOfDiamensions, pointsArray);
		else {
//			System.out.println("startIndex ="+ startIndex);
//			System.out.println( "startIndex+midIndex-1 ="+ (startIndex+midIndex-1));
			countUsingAlgorithmOne(startIndex, startIndex+midIndex-1, numOfDiamensions, pointsArray);
//			System.out.println("startIndex+midIndex = "+ startIndex+midIndex);
//			System.out.println("endIndex ="+ endIndex);
			countUsingAlgorithmOne(startIndex+midIndex, endIndex, numOfDiamensions, pointsArray);
		}
		for (int i=0; i<endIndex; i++)
			if (carPointsArray.get(i).get(0) != -999.999f)
				interestingPointsCount++;

		return interestingPointsCount;
	}
	
	public static void SimpleAlgo3(int start, int end, int d, ArrayList<ArrayList<Float>> pointsArray) {
//		System.out.println("start "+start);
//		System.out.println("end "+end);
		for (int i = start -1; i < end; i++) {
			for (int j = start -1; j < end; j++) {
//				System.out.println("i "+i);
				if (carPointsArray.get(i).get(0) != -999.999f) {
					boolean worse_somewhere = false;
					for (int k = 0; k < d; k++)
						if (pointsArray.get(i).get(k) < pointsArray.get(j).get(k)) {
							worse_somewhere = true;
//							System.out.println("i " + i + " j " + j + " k " + k);
//							System.out.println("pointsArray.i.k < pointsArray.j.k::::: " + pointsArray.get(i).get(k) + " :: " + pointsArray.get(j).get(k));
						}

					boolean not_better = true;
					for (int k = 0; k < d; k++)
						if (pointsArray.get(i).get(k) > pointsArray.get(j).get(k)) {
							not_better = false;
//							System.out.println("i "+i+" j "+j+ " k "+k);
//							System.out.println("pointsArray.i.k > pointsArray.j.k::::: "+pointsArray.get(i).get(k) +" :: "+ pointsArray.get(j).get(k)); 
						}

					if (worse_somewhere && not_better) {
//						System.out.println(pointsArray.get(i));
						pointsArray.get(i).set(0, -999.999f);
					}
				}
			}
		}
		/*//Printing interesting points
		 * for (int i=0; i<n; i++) if (isBest.get(i)) { for (int k=0; k<d; k++)
		 * System.out.print(pointsArray.get(i).get(k) + ""); System.out.println(); }
		 */
	}
	
	/* Sample algorithm given by professor */
	public static int SimpleAlgo2(int n, int d, ArrayList<ArrayList<Float>> pointsArray) {
		List<Boolean> isBest = new ArrayList<Boolean>(Collections.nCopies(n + 10, true));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isBest.get(i)) {
					boolean worse_somewhere = false;
					for (int k = 0; k < d; k++)
						if (pointsArray.get(i).get(k) < pointsArray.get(j).get(k)) {
							worse_somewhere = true;
//							System.out.println("i " + i + " j " + j + " k " + k);
//							System.out.println("pointsArray.i.k < pointsArray.j.k::::: " + pointsArray.get(i).get(k) + " :: " + pointsArray.get(j).get(k));
						}

					boolean not_better = true;
					for (int k = 0; k < d; k++)
						if (pointsArray.get(i).get(k) > pointsArray.get(j).get(k)) {
							not_better = false;
//							System.out.println("i "+i+" j "+j+ " k "+k);
//							System.out.println("pointsArray.i.k > pointsArray.j.k::::: "+pointsArray.get(i).get(k) +" :: "+ pointsArray.get(j).get(k)); 
						}

					if (worse_somewhere && not_better) {
						isBest.set(i, false);
//						System.out.println(pointsArray.get(i));
						pointsArray.get(i).set(0, -999.999f);
					}
				}
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++)
			if (isBest.get(i))
				count++;
		/*//Printing interesting points
		 * for (int i=0; i<n; i++) if (isBest.get(i)) { for (int k=0; k<d; k++)
		 * System.out.print(pointsArray.get(i).get(k) + ""); System.out.println(); }
		 */
		return count;
	}

	
	/* Main method */
	public static void main(String[] args) throws InterruptedException {
		carPointsArray = new ArrayList<ArrayList<Float>>(); // This holds the dimensional array
		long start, end;
		
//		readInput(5,2); /* This can be used for small input set */
		String filePath = System.getProperty("user.dir") + "\\src\\mainpkg\\" + "4.in"; /* Specify your file name here */
		
		/* Read and populate the Array */
		carPointsArray = readInput(filePath);
		System.out.println("Number of rows: "+rows);
		System.out.println("Number of dimensions: "+dimensions);
		
	/*	// Record starting time 
		start = System.nanoTime(); 
		// Calling SimpleAlgo2 
		System.out.println("\nNumber of interesting points using SimpleAlgo2 : "+ SimpleAlgo2(rows, dimensions, carPointsArray));
		// Record ending time 
		end = System.nanoTime(); 
		System.out.println("Time taken by SimpleAlgo2 is " + (end-start) + " nanoseconds!");
		
/*		// Printing interesting points
				System.out.println("\n\nInteresting points are : ");
				for (int i = 0; i < rows; i++)
					if (carPointsArray.get(i).get(0) != -999.999f) {
						for (int k = 0; k < dimensions; k++)
							System.out.print(carPointsArray.get(i).get(k) + "\t");
						System.out.println();
					} */
		
		// Record starting time
		start = System.nanoTime();
		// Calling SimpleAlgo2
		System.out.println("\nNumber of interesting points using AlgorithmOne : " + countUsingAlgorithmOne(1,rows, dimensions, carPointsArray));
		// Record ending time
		end = System.nanoTime();
		System.out.println("Time taken by Algorithm01 is " + (end - start) + " nanoseconds!");

		// Printing interesting points
		System.out.println("\n\nInteresting points are : ");
		for (int i = 0; i < rows; i++)
			if (carPointsArray.get(i).get(0) != -999.999f) {
				for (int k = 0; k < dimensions; k++)
					System.out.print(carPointsArray.get(i).get(k) + "\t");
				System.out.println();
			} 

	}

}
