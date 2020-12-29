/*
Heading:

Name: Eli Olcott
Date Started: 11/28/20
Title: Array Manipulation
Purpose: Create methods which manipulate the given sets of numbers stored in arrays.

*/

import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;

public class arrayManipulation {

  public arrayManipulation() {
    menu();
  }

  public void menu() {
    Scanner input = new Scanner(System.in);
    int choice = 0;

    int[] array = createArray();

    do {
      System.out.println("\nWelcome to Eli's Task Program: please select a task or type '-1' to quit:");
      System.out.println("0. Run programming reading data from text filePrint");
      System.out.println("1. Find the mean");
      System.out.println("2. Find the median");
      System.out.println("3. Find the mode");
      System.out.println("4. Find the range");
      System.out.println("5. Find the standard deviation");
      System.out.println("6. Create new array");
      choice = input.nextInt();

      if (choice == 0) {
        fileMethod();
      } else if (choice == 1) {
        double numMean = mean(array);
        System.out.print("\nThe mean of this is " + numMean + "\n");
      } else if (choice == 2) {
        double numMedian = median(array);
        System.out.println("\nThe median is " + numMedian + "\n");
      } else if (choice == 3) {
        mode(array);
      } else if (choice == 4) {
        int numRange = range(array);
        System.out.print("The range of the array is " + numRange + ".\n");
      } else if (choice == 5) {
        double stD = standardDeviation(array);
        System.out.print("The standard deviation of the array is " + stD + ".\n");
      } else if (choice == 6) {
        array = createArray();
      }
    } while (choice != -1);
    System.out.println("Thank You for using my program.");
  }

  public int randomNum (int upperLimit, int lowerLimit) {//This will be inclusive for both the upper and lower limit
    Random rand = new Random();
    int randomNum = rand.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
    return randomNum;
  }

  public int[] createArray() {
    int sizeArray = randomNum(50, 30);

    int[] array = new int[sizeArray];

    for (int i = 0; i < sizeArray; i++) {
      array[i] = randomNum(100, -100);
    }

    display(array);

    return array;
  }

  public void display (int[] array) {
    System.out.print("\nSize of Array: " + array.length);

    // System.out.print("\nArray positions: ");
    // for (int i = 0; i < array.length; i++) {
    //   System.out.format("%6d", i);
    // }

    System.out.print("\nArray entries:   ");
    for (int i = 0; i < array.length; i++) {
      System.out.format("%6d", array[i]);
    }
  }

  public int[] sort (int[] array) {
    int temp;
    for (int i = 0; i < array.length; i++) {
      for (int a = i + 1; a < array.length; a++) {
        if (array[i] > array[a]) {
          temp = array[i];
          array[i] = array[a];
          array[a] = temp;
        }
      }
    }
    return array;
  }

  public double mean(int[] array) {
    double mean = 0;
    for (int i = 0; i < array.length; i++) {
      mean += array[i];
    }
    mean = mean/array.length;
    return mean;
  }

  public double median (int[] array) {
    double medianNum = 0;
    int[] sortedArray = sort(array);
    if (sortedArray.length % 2 == 1) {
      int medianNumPos = (int) (sortedArray.length / 2);
      medianNum = sortedArray[medianNumPos];
    } else {
      int medianNumPos = sortedArray.length / 2;
      medianNum = (sortedArray[medianNumPos] + sortedArray[medianNumPos - 1]);
      medianNum = (double) (medianNum)/2;
    }
    return medianNum;
  }

  public int[] delete(int[] array) {
    int newArraySize = 0;
    for (int i = 0; i< array.length; i++) {
      if (array[i] != 101) {
        newArraySize++;
      }
    }
    int[] newArray = new int[newArraySize];
    int currentArrayPos = 0;

    for (int i = 0; i< array.length; i++) {
      if (array[i] != 101) {
        newArray [currentArrayPos] = array[i];
        currentArrayPos++;
      }
    }

    return newArray;
  }

  public void mode(int[] array) {
    int modeArraySize = 0;
    int maxCount = 0;
    for (int i = 0; i < array.length; i++) {
      int count = 0;
      for (int a = 0; a < array.length; a++) {
        if (array[a] == array[i])
          count++;
      }

      if (count >= maxCount) {
        modeArraySize++;
        maxCount = count;
      }
    }

    int[] modeNums = new int[modeArraySize];
    int modeNumPos = 0;

    for (int b = 0; b < modeArraySize; b++) {
      modeNums[b] = 101;
    }
    
    for (int i = 0; i < array.length; i++) {
      int count = 0;
      for (int a = 0; a < array.length; a++) {
        if (array[a] == array[i])
          count++;
      }

      if (count == maxCount) {
        boolean shouldCount = true;
        for (int b = 0; b < modeArraySize; b++) {
          if (array[i] == modeNums[b]) {
            shouldCount = false;
          }
        }

        if (shouldCount) {
          modeNums[modeNumPos] = array[i];
          modeNumPos++;
        }
      }
    }

    modeNums = delete(modeNums);
    modeNums = sort(modeNums);

    if (modeNums.length == 1) {
      System.out.print("\nThe mode is " + modeNums[0] + "\n");
      System.out.print("With " + maxCount + " occurences.\n");
    } else if (modeNums.length == 2) {
      System.out.print("\nThe two modes are " + modeNums[0] + " and " + modeNums[1] + "\n");
      System.out.print("With " + maxCount + " occurences.\n");
    } else {
      System.out.print("\nThere is no mode/modes, but the numbers with the most occurences are " );
      for (int i = 0; i < modeNums.length; i++) {
        if (i != modeNums.length - 1) {
          System.out.print(modeNums[i] + ", ");
        } else {
          System.out.print("and " + modeNums[i] + ".\n");
        }
      }
      System.out.print("With " + maxCount + " occurences.\n");
    }
  }

  public int range (int[] array) {
    int[] sortedArray = sort(array);
    int range = sortedArray[sortedArray.length-1] - sortedArray[0];

    return range;
  }

  public double standardDeviation (int[] array) {
    double stD = 0;
    double mean = mean(array);
    for (int i = 0; i < array.length; i++) {
      stD += (array[i] - mean) * (array[i] - mean);
    }
    stD = stD / (array.length - 1);
    double newstD = Math.sqrt(stD);
    return newstD;
  }

  public void fileMethod() {
    String pathname = "Lab2TestData.txt";
    File file = new File(pathname);
    Scanner input = null;
    try 
    {
        input = new Scanner(file);
    } 
    catch (FileNotFoundException ex) 
    {
        System.out.println("*** Cannot open " + pathname + " ***");
        System.exit(1);
    }
    int size = input.nextInt();			// Size of array
    int[] list = new int[size];			// Declare array
    for (int x = 0; x < size; x++)			// Fill array
    list[x] = input.nextInt();	
      while (input.hasNextInt()) 			// Loop Menu
      {
    switch (input.nextInt()) 
          {
          case 1: 
              {
        System.out.println("The Mean is: " + mean(list));
          }
          break;
          case 2: 
              {
        System.out.println("The Median is: " + median(list));
          }
          break;
              case 3: 
              {
          mode(list);
          }
          break;
              case 4: 
              {
          System.out.println("The Range is: " + range(list));
          }
          break;
          case 5: 
          {
      System.out.println("The Standard Deviation is: " +    
      standardDeviation(list));
          }
          break;
    
      }
      }
  }
}