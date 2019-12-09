/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jxg4315lab4part1a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Joshua Gridley
 * UTA ID: 1001634315
 * @author jxg4315
 */
public class JXG4315Lab4Part1a {

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
        
        String[] robotName = new String[MAXSIZE];
        int[] robotYear = new int[MAXSIZE];
        String[] robotKind = new String[MAXSIZE];
        char[] robotSource = new char[MAXSIZE];
        String[] robotCost = new String[MAXSIZE];
        double[] robotCostValue = new double[MAXSIZE];
        String[] robotSourceTitle = new String[MAXSIZE];
        double[] robotHeight = new double[MAXSIZE];
        double[] robotWeight = new double[MAXSIZE];
        char[] robotImplemented = new char[MAXSIZE];
        String[] robotActor = new String[MAXSIZE];
        
        for ( int k = 0; k < MAXSIZE; k++ )
        {
            robotName[k] = "";
            robotKind[k] = "";
            robotCost[k] = "";
            robotSourceTitle[k] = "";
            robotActor[k] = "";
        }
        
        String fileName = "robotsInfoHW";
        
        File nameFile = new File((fileName + ".txt"));
        Scanner inputFile;
        
        PrintWriter outFile = new PrintWriter(System.out);
        
        try 
        {
            inputFile = new Scanner(nameFile);
            System.out.println("File " + nameFile + " found.\n");
            outFile = new PrintWriter("JXG4315Lab4Part1a.out");
            System.out.println("Output data file is JXG4315Lab4Part1a.out");
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
                robotName[ri] = tempName;
                outFile.println("robotName["+ri+"] = " + robotName[ri]);
                robotYear[ri] = tempYear;
                outFile.println("robotYear["+ri+"] = " + robotYear[ri]);
                robotKind[ri] = tempKind;
                outFile.println("robotKind["+ri+"] = " + robotKind[ri]);
                robotSource[ri] = tempSource;
                outFile.println("robotSource["+ri+"] = " + robotSource[ri]);
                robotCost[ri] = tempCost;
                if (robotCost[ri].contains("K"))
                {
                    robotCostValue[ri] = Double.parseDouble(robotCost[ri].replace("K", ""));
                    robotCostValue[ri] = robotCostValue[ri] * 1000;
                } 
                if (robotCost[ri].contains("M"))
                {
                    robotCostValue[ri] = Double.parseDouble(robotCost[ri].replace("M", ""));
                    robotCostValue[ri] = robotCostValue[ri] * 1000000;
                }
                outFile.println("robotCostValue["+ri+"] = $" + robotCostValue[ri]);
                robotSourceTitle[ri] = tempSourceTitle;
                outFile.println("robotSourceTitle["+ri+"] = " + robotSourceTitle[ri]);
                robotHeight[ri] = tempHeight;
                outFile.println("robotHeight["+ri+"] = " + robotHeight[ri]);
                robotWeight[ri] = tempWeight;
                outFile.println("robotWeight["+ri+"] = " + robotWeight[ri]);
                robotImplemented[ri] = tempImplemented;
                outFile.println("robotImplemented["+ri+"] = " + robotImplemented[ri]);
                robotActor[ri] = tempActor;
                outFile.println("robotActor["+ri+"] = " + robotActor[ri] + "\n");
            }
            ri++;
        }
        
        System.out.println("");
        for ( int i = 0; i < ri; i++ )
        {
            try
            {
            String firstLetter = robotKind[i].substring(0, 1).toUpperCase();
            String uppercaseFirst = firstLetter + robotKind[i].substring(1);
            robotKind[i] = uppercaseFirst;
            System.out.println(robotKind[i] + " robot " + (i+1) + ": " + robotName[i] + " debuted " + robotYear[i] + 
                    " in " + (robotSource[i] == 'R'? "real life from " + robotSourceTitle[i]
                            + " at the cost of $" + robotCostValue[i] + "0.":
                            ((robotSource[i] == 'B'? "the book":
                            (robotSource[i] == 'M'? "the movie":
                            (robotSource[i] == 'T'? "the TV show": "the play")))
                            + " \"" + robotSourceTitle[i] + "\" at a cost of $"
                            + robotCostValue[i] + "0.")));
            outFile.println(robotKind[i] + " robot " + (i+1) + ": " + robotName[i] + " debuted " + robotYear[i] + 
                    " in " + (robotSource[i] == 'R'? "real life from " + robotSourceTitle[i]
                            + " at the cost of $" + robotCostValue[i] + "0.":
                            ((robotSource[i] == 'B'? "the book":
                            (robotSource[i] == 'M'? "the movie":
                            (robotSource[i] == 'T'? "the TV show": "the play")))
                            + " \"" + robotSourceTitle[i] + "\" at a cost of $"
                            + robotCostValue[i] + "0.")));
            }
            catch (StringIndexOutOfBoundsException sioobe)
            {
                System.out.println("Robot data invalid for robot " + (i + 1));
                outFile.println("Robot data invalid for robot " + (i + 1));
            }
        }
        
        bubbleSort(robotCostValue, robotYear, robotSource, robotName, robotKind, robotSourceTitle);
        printRobotTable(robotName, robotYear, robotCostValue, robotSource, robotKind, robotSourceTitle, outFile);
        
        outFile.close();
    }
    
    public static void bubbleSort(double[] robotCostValue, int[] robotYear, char[] robotSource, String[] robotName, String[] robotKind, String[] robotSourceTitle) 
    {
        double tempCostValue = -1;
        int tempYear = -1;
        char tempSource = ' ';
        String tempName = "";
        String tempKind = "";
        String tempSourceTitle = "";
        
        for ( int outer = 0; outer < robotCostValue.length-1; outer++ )
            for ( int inner = 0; inner < robotCostValue.length-1; inner++ )
            {
                if ((robotCostValue[inner]) > (robotCostValue[inner + 1]))
                {
                    tempCostValue = robotCostValue[inner];
                    robotCostValue[inner] = robotCostValue[inner + 1];
                    robotCostValue[inner + 1] = tempCostValue;
                    
                    tempYear = robotYear[inner];
                    robotYear[inner] = robotYear[inner + 1];
                    robotYear[inner + 1] = tempYear;
                    
                    tempSource = robotSource[inner];
                    robotSource[inner] = robotSource[inner + 1];
                    robotSource[inner + 1] = tempSource;
                    
                    tempName = robotName[inner];
                    robotName[inner] = robotName[inner + 1];
                    robotName[inner + 1] = tempName;
                    
                    tempKind = robotKind[inner];
                    robotKind[inner] = robotKind[inner + 1];
                    robotKind[inner + 1] = tempKind;
                    
                    tempSourceTitle = robotSourceTitle[inner];
                    robotSourceTitle[inner] = robotSourceTitle[inner + 1];
                    robotSourceTitle[inner + 1] = tempSourceTitle;
                }
            }
    }
    
    public static void printRobotTable(String[] robotName, int[] robotYear, double[] robotCostValue, char[] robotSource, String[] robotKind, String[] robotSourceTitle, PrintWriter outFile) {
        System.out.println("\nTable of Robot Info");
        System.out.printf("%15s%6s%12s%8s%17s%7s\n", "NAME", "YEAR", "COST", "SOURCE", "CATEGORY", "TITLE");
        for ( int i = 0; i < robotYear.length; i++ )
        {
            if (robotYear[i] != 0)
            {
                System.out.printf("%15s%6d%11s0%3c%22s  %-40s\n", robotName[i], robotYear[i], robotCostValue[i], robotSource[i], robotKind[i], robotSourceTitle[i]);
            }
        }
        
        outFile.println("\nTable of Robot Info");
        outFile.printf("%15s%6s%12s%8s%17s%7s\n", "NAME", "YEAR", "COST", "SOURCE", "CATEGORY", "TITLE");
        for ( int i = 0; i < robotYear.length; i++ )
        {
            if (robotYear[i] != 0)
            {
                outFile.printf("%15s%6d%11s0%3c%22s  %-40s\n", robotName[i], robotYear[i], robotCostValue[i], robotSource[i], robotKind[i], robotSourceTitle[i]);
            }
        }
    }
    
}
