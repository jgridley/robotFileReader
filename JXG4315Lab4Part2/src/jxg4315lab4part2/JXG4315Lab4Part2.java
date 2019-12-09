/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jxg4315lab4part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Joshua Gridley
 * UTA ID: 1001634315
 * @author jxg4315
 */
public class JXG4315Lab4Part2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final int MAXSIZE = 30;

        String tempName = null; // robot name
        int tempYear = 0;    // robot year
        String tempKind; // robot type
        char tempSource; // robot source
        String tempCost; // robot cost
        String tempSourceTitle; // robot place title
        double tempHeight; // robot height
        double tempWeight; // robot weight
        char tempImplemented;
        String tempActor;
        
        // [0] = name, [1] = catagory, [2] = title, [3] = actor, [4] = cost
        String[][] robotStrs = new String[MAXSIZE][5];
        final int NAME = 0;
        final int KIND = 1;
        final int TITLE = 2;
        final int ACTOR = 3;
        final int COST = 4;
        // [0] = year
        int[][] robotInts = new int[MAXSIZE][2];
        final int YEAR = 0;
        // [0] = source, [1] = implemented
        char[][] robotChars = new char[MAXSIZE][2];
        final int SOURCE = 0;
        final int IMP = 1;
        // [0] = height, [1] = weight, [2] total cost value
        double[][] robotDbls = new double[MAXSIZE][3];
        final int HEIGHT = 0;
        final int WEIGHT = 1;
        final int TCOST = 2;
        
        
        for ( int k = 0; k < MAXSIZE; k++ )
        {
            for ( int i = 0; i < COST; i++ )
            {
                robotStrs[k][i] = "";
            }
        }
        
        String fileName = "robotsInfoHW";
        
        File nameFile = new File((fileName + ".txt"));
        Scanner inputFile;
        
        PrintWriter outFile = new PrintWriter(System.out);
        
        try 
        {
            inputFile = new Scanner(nameFile);
            System.out.println("File " + nameFile + " found.\n");
            outFile = new PrintWriter("JXG4315Lab4Part2.out");
            System.out.println("Output data file is JXG4315Lab4Part2.out");
        }
        catch (FileNotFoundException fnfe)
        {
            inputFile = new Scanner(System.in);
            System.out.println("Input file robotsInfoHW.txt is not found.");
        }
        
        boolean inputValid;
        int ri = 0;
        
        while (inputFile.hasNext())
        {
            inputValid = true;
            tempName = inputFile.next();
            try
            {
                tempYear = inputFile.nextInt();
            }
            catch (InputMismatchException ime)
            {
                tempYear = -1;
                inputValid = false;
            }
            catch (NoSuchElementException nsee)
            {
                System.out.println("\n-- Missing input data: " + nsee);
                outFile.println("\n-- Missing input data: " + nsee);
                inputValid = false;
            }
                    
            tempKind = inputFile.next();
            tempSource = inputFile.next().charAt(0);
            if (Character.isDigit(tempSource))
            {
                System.out.println("\n-- Source is not valid for " + tempName);
                outFile.println("\n-- Source is not valid for " + tempName + "\n");
                inputValid = false;
            }
            else {}
            tempCost = inputFile.next();
            
            String tTabs = inputFile.nextLine();
            tempSourceTitle = tTabs.substring(0);
            Scanner inTab = new Scanner(tempSourceTitle);
            String[] tabWds = new String[20];
            inTab.useDelimiter(", ");
            for ( int i = 0 ; (i < 20) && inTab.hasNext() ; i++)
            {
                tabWds[i] = inTab.next().trim();
            }
            inTab.reset();
            
            tempSourceTitle = tabWds[0];
            tempHeight = Double.parseDouble(tabWds[1]);
            tempWeight = Double.parseDouble(tabWds[2]);
            tempImplemented = tabWds[3].charAt(0);
            tempActor = tabWds[4];
            
            if (inputValid)
            {
                // Strings
                robotStrs[ri][NAME] = tempName;
                outFile.println("Name = robotStrs["+ri+"]["+NAME+"] = " + robotStrs[ri][NAME]);
                robotStrs[ri][KIND] = tempKind;
                outFile.println("Kind = robotStrs["+ri+"]["+KIND+"] = " + robotInts[ri][KIND]);
                robotStrs[ri][TITLE] = tempSourceTitle;
                outFile.println("Source Title = robotStrs["+ri+"]["+TITLE+"] = " + robotStrs[ri][TITLE]);
                robotStrs[ri][ACTOR] = tempActor;
                if (robotStrs[ri][ACTOR] == null)
                {
                    robotStrs[ri][ACTOR] = "N/A";
                }
                else {}
                outFile.println("Actor = robotStrs["+ri+"]["+ACTOR+"] = " + robotStrs[ri][ACTOR]);
                // Integers
                robotInts[ri][YEAR] = tempYear;
                outFile.println("Year = robotInts["+ri+"]["+YEAR+"] = " + robotInts[ri][YEAR]);
                // Doubles
                robotStrs[ri][COST] = tempCost;
                if (robotStrs[ri][COST].contains("K"))
                {
                    robotDbls[ri][TCOST] = Double.parseDouble(robotStrs[ri][COST].replace("K", ""));
                    robotDbls[ri][TCOST] = robotDbls[ri][TCOST] * 1000;
                } 
                if (robotStrs[ri][COST].contains("M"))
                {
                    robotDbls[ri][TCOST] = Double.parseDouble(robotStrs[ri][COST].replace("M", ""));
                    robotDbls[ri][TCOST] = robotDbls[ri][TCOST] * 1000000;
                }
                outFile.println("Total Cost = robotDbls["+ri+"]["+TCOST+"] = " + robotDbls[ri][TCOST]);
                robotDbls[ri][HEIGHT] = tempHeight;
                outFile.println("Height = robotDbls["+ri+"]["+HEIGHT+"] = " + robotDbls[ri][HEIGHT]);
                robotDbls[ri][WEIGHT] = tempWeight;
                outFile.println("Weight = robotDbls["+ri+"]["+WEIGHT+"] = " + robotDbls[ri][WEIGHT]);
                // Characters
                robotChars[ri][SOURCE] = tempSource;
                outFile.println("Source = robotChars["+ri+"]["+SOURCE+"] = " + robotInts[ri][SOURCE]);
                robotChars[ri][IMP] = tempImplemented;
                outFile.println("Implemented = robotChars["+ri+"]["+IMP+"] = " + robotChars[ri][IMP] + "\n");
            }
            ri++;
        }
        
        System.out.println("");
        for ( int i = 0; i < ri; i++ )
        {
            try
            {
            String firstLetter = robotStrs[i][KIND].substring(0, 1).toUpperCase();
            String uppercaseFirst = firstLetter + robotStrs[i][KIND].substring(1);
            robotStrs[i][KIND] = uppercaseFirst;
            System.out.println(robotStrs[i][KIND] + " robot " + (i+1) + ": " + robotStrs[i][NAME] + " debuted " + robotInts[i][YEAR] + 
                    " in " + (robotChars[i][SOURCE] == 'R'? "real life from " + robotStrs[i][TITLE]
                            + " at the cost of $" + robotDbls[i][TCOST]:
                            ((robotChars[i][SOURCE] == 'B'? "the book":
                            (robotChars[i][SOURCE] == 'M'? "the movie":
                            (robotChars[i][SOURCE] == 'T'? "the TV show": "the play")))
                            + " \"" + robotStrs[i][TITLE] + "\" at a cost of $"
                            + robotDbls[i][TCOST])));
            outFile.println(robotStrs[i][KIND] + " robot " + (i+1) + ": " + robotStrs[i][NAME] + " debuted " + robotInts[i][YEAR] + 
                    " in " + (robotChars[i][SOURCE] == 'R'? "real life from " + robotStrs[i][TITLE]
                            + " at the cost of $" + robotDbls[i][TCOST]:
                            ((robotChars[i][SOURCE] == 'B'? "the book":
                            (robotChars[i][SOURCE] == 'M'? "the movie":
                            (robotChars[i][SOURCE] == 'T'? "the TV show": "the play")))
                            + " \"" + robotStrs[i][TITLE] + "\" at a cost of $"
                            + robotDbls[i][TCOST])));
            }
            catch (StringIndexOutOfBoundsException | NullPointerException npe)
            {
                System.out.println("Robot data invalid for robot " + (i + 1));
                outFile.println("Robot data invalid for robot " + (i + 1));
            }
        }
        outFile.println("");
        
        bubbleSort(robotStrs, robotInts, robotDbls, robotChars, NAME, outFile);
        printRobotTable(robotStrs, robotInts, robotDbls, robotChars, outFile);
        
        outFile.close();
    }
    
    public static void bubbleSort(String[][] names, int[][] vals, double[][] doubles, char[][] types, int sortBy, PrintWriter outFile) 
    {
        double[] tempDbl;
        int[] tempInt;
        char[] tempChar;
        String[] tempStr;
        
        System.out.println("\nSorted Alphabetically");
        outFile.println("Sorted Alphabetically");
        
        for ( int outer = 0; outer < vals.length-1; outer++ )
            for ( int inner = 0; inner < vals.length-1; inner++ )
            {
                if ((names[inner][sortBy] != null) && (names[inner + 1][sortBy] != null))
                    if (names[inner][sortBy].compareTo(names[inner + 1][sortBy]) > 0)
                    {
                        tempStr = names[inner];
                        names[inner] = names[inner + 1];
                        names[inner + 1] = tempStr;
                        outFile.println("Swapped " + Arrays.toString(names[inner]) + " with " + Arrays.toString(tempStr));

                        tempInt = vals[inner];
                        vals[inner] = vals[inner + 1];
                        vals[inner + 1] = tempInt;

                        tempDbl = doubles[inner];
                        doubles[inner] = doubles[inner + 1];
                        doubles[inner + 1] = tempDbl;

                        tempChar = types[inner];
                        types[inner] = types[inner + 1];
                        types[inner + 1] = tempChar;
                    }
            }
    }
    
    public static void printRobotTable(String[][] robotStrs, int[][] robotInts, double[][] robotDbls, char[][] robotChars, PrintWriter outFile) {
        final int NAME = 0;
        final int SOURCE = 0;
        final int YEAR = 0;
        final int KIND = 1;
        final int TITLE = 2;
        final int COST = 2;
        final int HEIGHT = 0;
        final int WEIGHT = 1;
        final int ACTOR = 3;
        System.out.println("\nTable of Robot Info");
        System.out.printf("%15s%7s%11s%8s%10s%10s%20s%22s%7s\n", "NAME", "YEAR", "COST", "SOURCE", "HEIGHT", "WEIGHT", "ACTOR", "CATEGORY", "TITLE");
        for ( int i = 0; i < robotInts.length; i++ )
        {
            if (robotInts[i][YEAR] != 0)
            {
                System.out.printf("%15s%7d%11s%3c%15s%10s%20s%22s  %-40s\n", robotStrs[i][NAME], robotInts[i][YEAR], robotDbls[i][COST], robotChars[i][SOURCE], robotDbls[i][HEIGHT], robotDbls[i][WEIGHT], robotStrs[i][ACTOR], robotStrs[i][KIND], robotStrs[i][TITLE]);
            }
        }
        
        outFile.println("\nTable of Robot Info");
        outFile.printf("%15s%7s%11s%8s%10s%10s%20s%22s%7s\n", "NAME", "YEAR", "COST", "SOURCE", "HEIGHT", "WEIGHT", "ACTOR", "CATEGORY", "TITLE");
        for ( int i = 0; i < robotInts.length; i++ )
        {
            if (robotInts[i][YEAR] != 0)
            {
                outFile.printf("%15s%7d%11s%3c%15s%10s%20s%22s  %-40s\n", robotStrs[i][NAME], robotInts[i][YEAR], robotDbls[i][COST], robotChars[i][SOURCE], robotDbls[i][HEIGHT], robotDbls[i][WEIGHT], robotStrs[i][ACTOR], robotStrs[i][KIND], robotStrs[i][TITLE]);
            }
        }
    }
    
}
